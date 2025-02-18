package org.example.payment.controller;

import org.example.payment.dto.PaymentRequest;
import org.springframework.http.ResponseEntity;

public interface PaymentController {
    void handlePayment(PaymentRequest request);
    void handleCancellation(PaymentRequest request);
}

