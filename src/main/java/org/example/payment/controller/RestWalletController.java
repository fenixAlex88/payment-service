package org.example.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payment.entity.Wallet;
import org.example.payment.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
@Slf4j
public class RestWalletController implements WalletController {
    private final WalletService walletService;

    @Override
    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestParam Long userId) {
        log.info("Creating wallet for user: {}", userId);
        return ResponseEntity.ok(walletService.createWallet(userId));
    }

    @Override
    @PutMapping("/{userId}/top-up")
    public ResponseEntity<Wallet> topUpBalance(
            @PathVariable Long userId,
            @RequestParam BigDecimal amount
    ) {
        log.info("Topping up balance for user: {}, amount: {}", userId, amount);
        return ResponseEntity.ok(walletService.replenishBalance(userId, amount));
    }

    @Override
    @GetMapping("/{userId}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long userId) {
        BigDecimal balance = walletService.getBalance(userId);
        log.info("Getting balance for user: {}, balance: {}", userId, balance);
        return ResponseEntity.ok(balance);
    }
}
