package org.example.paymentservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.paymentservice.model.dto.request.TopUpRequest;
import org.example.paymentservice.model.dto.request.WalletCreateRequest;
import org.example.paymentservice.model.dto.response.WalletResponse;
import org.example.paymentservice.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<WalletResponse> createWallet(@Valid @RequestBody WalletCreateRequest request) {
        return ResponseEntity.ok(walletService.createWallet(request));
    }

    @PutMapping("/{userId}/top-up")
    public ResponseEntity<WalletResponse> topUpBalance(
            @PathVariable @NotNull(message = "User ID is required") Long userId,
            @Valid @RequestBody TopUpRequest topUpRequest
    ) {
        BigDecimal amount = topUpRequest.amount();
        return ResponseEntity.ok(walletService.replenishBalance(userId, amount));
    }

    @GetMapping("/{userId}/balance")
    public ResponseEntity<WalletResponse> getBalance(@PathVariable @NotNull(message = "User ID is required") Long userId) {
        return ResponseEntity.ok(walletService.getBalance(userId));
    }
}


