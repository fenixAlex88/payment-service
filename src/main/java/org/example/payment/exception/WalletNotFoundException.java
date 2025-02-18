package org.example.payment.exception;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(Long userId) {
        super("Wallet not found for user: " + userId);
    }
}
