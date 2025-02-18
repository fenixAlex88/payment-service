package org.example.payment.exception;

public class PaymentAlreadyCancelledException extends RuntimeException {
    public PaymentAlreadyCancelledException(Long paymentId) {
        super("Payment with ID " + paymentId + " is already cancelled");
    }
}
