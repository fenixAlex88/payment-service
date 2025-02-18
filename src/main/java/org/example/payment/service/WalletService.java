package org.example.payment.service;

import org.example.payment.entity.Wallet;

import java.math.BigDecimal;

public interface WalletService {
    Wallet createWallet(Long userId);

    Wallet replenishBalance(Long userId, BigDecimal amount);

    BigDecimal getBalance(Long userId);
}