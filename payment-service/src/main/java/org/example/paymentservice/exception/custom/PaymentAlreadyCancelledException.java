package org.example.paymentservice.exception.custom;

public class PaymentAlreadyCancelledException extends RuntimeException {
    public PaymentAlreadyCancelledException(Long paymentId) {
        super("Payment with ID " + paymentId + " is already cancelled");
    }
}
