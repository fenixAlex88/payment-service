package org.example.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payment.dto.OrderEvent;
import org.example.payment.exception.InsufficientBalanceException;
import org.example.payment.exception.WalletNotFoundException;
import org.example.payment.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentListener {

    private final PaymentService paymentService;

    @KafkaListener(topics = "payment-service-topic", groupId = "payment-group")
    public void handleOrderEvent(OrderEvent order) {
        try {
            paymentService.processOrder(order);
        } catch (InsufficientBalanceException | WalletNotFoundException e) {
            log.error("Payment failed for order {}: {}", order.getOrderId(), e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error processing order {}", order.getOrderId(), e);
        }
    }
}
