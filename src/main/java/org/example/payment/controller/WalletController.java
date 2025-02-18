package org.example.payment.controller;

import org.example.payment.entity.Wallet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

public interface WalletController {
    ResponseEntity<Wallet> createWallet(@RequestParam Long userId);

    ResponseEntity<Wallet> topUpBalance(
            @PathVariable Long userId,
            @RequestParam BigDecimal amount
    );

    ResponseEntity<BigDecimal> getBalance(@PathVariable Long userId);
}

