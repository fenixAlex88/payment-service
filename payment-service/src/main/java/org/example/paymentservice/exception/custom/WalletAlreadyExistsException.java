package org.example.paymentservice.exception.custom;

import lombok.Getter;
import org.example.paymentservice.exception.base.BaseException;

@Getter
public class WalletAlreadyExistsException extends BaseException {

    public WalletAlreadyExistsException(Long userId) {
        super("Wallet already exists for user ID: " + userId, "Wallet already exists for user");
    }
}

