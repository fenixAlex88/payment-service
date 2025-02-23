package org.example.paymentservice.exception.custom;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(Long userId) {
        super("Insufficient balance for user: " + userId);
    }
}
