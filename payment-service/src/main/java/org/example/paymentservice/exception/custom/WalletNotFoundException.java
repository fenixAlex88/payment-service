package org.example.paymentservice.exception.custom;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(Long userId) {
        super("Wallet not found for user: " + userId);
    }
}
