package org.example.paymentservice.service;

import org.example.paymentservice.model.dto.request.WalletCreateRequest;
import org.example.paymentservice.model.dto.response.WalletResponse;

import java.math.BigDecimal;

public interface WalletService {

    WalletResponse createWallet(WalletCreateRequest request);

    WalletResponse replenishBalance(Long userId, BigDecimal amount);

    WalletResponse getBalance(Long userId);
}