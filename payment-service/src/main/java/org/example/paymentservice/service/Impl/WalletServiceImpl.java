package org.example.paymentservice.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentservice.exception.custom.UserNotFoundException;
import org.example.paymentservice.exception.custom.WalletAlreadyExistsException;
import org.example.paymentservice.model.dto.request.WalletCreateRequest;
import org.example.paymentservice.model.dto.response.WalletResponse;
import org.example.paymentservice.model.entity.Wallet;
import org.example.paymentservice.model.mapper.WalletCreateRequestToWallet;
import org.example.paymentservice.model.mapper.WalletToWalletResponse;
import org.example.paymentservice.repository.WalletRepository;
import org.example.paymentservice.service.WalletService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletCreateRequestToWallet walletCreateRequestToWalletMapper;
    private final WalletToWalletResponse walletToWalletResponseMapper;

    @Override
    @CacheEvict(value = "wallets", key = "#request.userId")
    @Transactional
    public WalletResponse createWallet(WalletCreateRequest request) {
        log.info("Creating wallet for user: {}", request.userId());

        if (walletRepository.existsByUserId(request.userId())) {
            log.warn("Wallet already exists for user: {}", request.userId());
            throw new WalletAlreadyExistsException(request.userId());
        }

        Wallet wallet = walletCreateRequestToWalletMapper.toWallet(request);
        Wallet savedWallet = walletRepository.save(wallet);

        log.info("Wallet created successfully for user: {}", request.userId());
        return walletToWalletResponseMapper.toWalletResponse(savedWallet);
    }

    @Override
    @CacheEvict(value = "wallets", key = "#userId")
    @Transactional
    public WalletResponse replenishBalance(Long userId, BigDecimal amount) {
        log.info("Topping up balance for user: {}, amount: {}", userId, amount);

        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        wallet.setBalance(wallet.getBalance().add(amount));
        Wallet savedWallet = walletRepository.save(wallet);

        log.info("Balance topped up successfully for user: {}, new balance: {}", userId, savedWallet.getBalance());
        return walletToWalletResponseMapper.toWalletResponse(savedWallet);
    }

    @Override
    @Cacheable(value = "wallets", key = "#userId")
    public WalletResponse getBalance(Long userId) {
        log.info("Getting balance for user: {}", userId);

        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        log.info("Balance retrieved for user: {}, balance: {}", userId, wallet.getBalance());
        return walletToWalletResponseMapper.toWalletResponse(wallet);
    }
}

