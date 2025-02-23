package org.example.paymentservice.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentservice.exception.custom.InsufficientBalanceException;
import org.example.paymentservice.exception.custom.WalletNotFoundException;
import org.example.paymentservice.model.dto.event.InventoryEventDto;
import org.example.paymentservice.model.dto.event.OrderEventDto;
import org.example.paymentservice.model.entity.Payment;
import org.example.paymentservice.model.entity.Wallet;
import org.example.paymentservice.model.mapper.OrderEventDtoToInventoryDto;
import org.example.paymentservice.model.mapper.OrderEventDtoToPayment;
import org.example.paymentservice.repository.PaymentRepository;
import org.example.paymentservice.repository.WalletRepository;
import org.example.paymentservice.service.PaymentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final WalletRepository walletRepository;
    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, InventoryEventDto> kafkaTemplate;
    private final OrderEventDtoToInventoryDto orderToInventoryMapper;
    private final OrderEventDtoToPayment orderToPaymentMapper;

    @CacheEvict(value = "wallets", key = "#orderEvent.userId")
    @Override
    @Transactional
    public void processOrder(OrderEventDto orderEvent) {
        Wallet wallet = walletRepository.findByUserId(orderEvent.getUserId())
                .orElseThrow(() -> new WalletNotFoundException(orderEvent.getUserId()));

        if (wallet.getBalance().compareTo(orderEvent.getTotalCost()) < 0) {
            throw new InsufficientBalanceException(orderEvent.getUserId());
        }

        wallet.setBalance(wallet.getBalance().subtract(orderEvent.getTotalCost()));
        walletRepository.save(wallet);

        Payment payment = orderToPaymentMapper.orderEventToPayment(orderEvent);
        payment.setWallet(wallet);
        paymentRepository.save(payment);

        InventoryEventDto inventoryEvent = orderToInventoryMapper.orderEventToInventoryEvent(orderEvent);
        kafkaTemplate.send("inventory-service-topic", inventoryEvent);

        log.info("Processed order {}", orderEvent.getOrderId());
    }
}


