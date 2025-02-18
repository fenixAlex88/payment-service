package org.example.payment.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(Long paymentId) {
        super("Payment not found for ID: " + paymentId);
    }
}
