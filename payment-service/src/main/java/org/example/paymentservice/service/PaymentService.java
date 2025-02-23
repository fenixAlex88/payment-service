package org.example.paymentservice.service;


import org.example.paymentservice.model.dto.event.OrderEventDto;

public interface PaymentService {
    void processOrder(OrderEventDto order);
//    void cancelPayment(Long paymentId, Long userId);
}
