package org.example.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payment.dto.InventoryEvent;
import org.example.payment.dto.OrderEvent;
import org.example.payment.dto.OrderToInventoryMapper;
import org.example.payment.dto.OrderToPaymentMapper;
import org.example.payment.entity.Payment;
import org.example.payment.entity.Wallet;
import org.example.payment.exception.InsufficientBalanceException;
import org.example.payment.exception.WalletNotFoundException;
import org.example.payment.repository.PaymentRepository;
import org.example.payment.repository.WalletRepository;
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
    private final KafkaTemplate<String, InventoryEvent> kafkaTemplate;
    private final OrderToInventoryMapper orderMapper;
    private final OrderToPaymentMapper orderToPaymentMapper;

    @CacheEvict(value = "wallets", key = "#order.userId")
    @Override
    @Transactional
    public void processOrder(OrderEvent order) {
        Wallet wallet = walletRepository.findByUserId(order.getUserId())
                .orElseThrow(() -> new WalletNotFoundException(order.getUserId()));

        if (wallet.getBalance().compareTo(order.getTotalCost()) < 0) {
            throw new InsufficientBalanceException(order.getUserId());
        }

        wallet.setBalance(wallet.getBalance().subtract(order.getTotalCost()));
        walletRepository.save(wallet);

        Payment payment = orderToPaymentMapper.orderEventToPayment(order);
        payment.setWallet(wallet);

        paymentRepository.save(payment);

        InventoryEvent inventoryEvent = orderMapper.orderEventToInventoryEvent(order);

        kafkaTemplate.send("inventory-service-topic", inventoryEvent);

        log.info("Processed order {}", order.getOrderId());
    }
}

