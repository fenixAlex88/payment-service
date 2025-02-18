package org.example.payment.service;

import org.example.payment.dto.OrderEvent;


public interface PaymentService {
    void processOrder(OrderEvent order);
//    void cancelPayment(Long paymentId, Long userId);
}
