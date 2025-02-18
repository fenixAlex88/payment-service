package org.example.payment.exception;

import lombok.Getter;

@Getter
public class WalletAlreadyExistsException extends CustomException {

    public WalletAlreadyExistsException(Long userId) {
        super("Wallet already exists for user ID: " + userId, "Wallet already exists for user");
    }
}

