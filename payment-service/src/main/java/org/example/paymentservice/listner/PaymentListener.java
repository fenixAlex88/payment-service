package org.example.paymentservice.listner;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentservice.exception.custom.InsufficientBalanceException;
import org.example.paymentservice.exception.custom.WalletNotFoundException;
import org.example.paymentservice.model.dto.event.OrderEventDto;
import org.example.paymentservice.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
@Validated
public class PaymentListener {

    private final PaymentService paymentService;

    @KafkaListener(topics = "payment-service-topic")
    public void handleOrderEvent(OrderEventDto order) {

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<OrderEventDto>> violations = validator.validate(order);

            if (!violations.isEmpty()) {
                for (ConstraintViolation<OrderEventDto> violation : violations) {
                    log.error("Validation error for order {}: {}", order.getOrderId(), violation.getMessage());
                }
                return;
            }
        }


            try {
            paymentService.processOrder(order);
        } catch (InsufficientBalanceException | WalletNotFoundException e) {
            log.error("Payment failed for order {}: {}", order.getOrderId(), e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error processing order {}", order.getOrderId(), e);
        }
    }
}
