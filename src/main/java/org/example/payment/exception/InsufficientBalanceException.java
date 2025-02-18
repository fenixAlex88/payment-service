package org.example.payment.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(Long userId) {
        super("Insufficient balance for user: " + userId);
    }
}
