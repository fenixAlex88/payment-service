package org.example.payment.service;

import lombok.RequiredArgsConstructor;
import org.example.payment.entity.Wallet;
import org.example.payment.exception.UserNotFoundException;
import org.example.payment.exception.WalletAlreadyExistsException;
import org.example.payment.repository.WalletRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Override
    @CacheEvict(value = "wallets", key = "#waletDto.userId")
    @Transactional
    public Wallet createWallet(WaletDto waletDto) {
        if (walletRepository.existsByUserId(userId)) {
            throw new WalletAlreadyExistsException(userId);
        }
        Wallet wallet = Wallet.builder()
                .userId(userId)
                .balance(BigDecimal.ZERO)
                .build();
        return walletRepository.save(wallet);
    }

    @Override
    @CacheEvict(value = "wallets", key = "#userId")
    @Transactional
    public Wallet replenishBalance(Long userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        wallet.setBalance(wallet.getBalance().add(amount));
        return wallet;
    }

    @Override
    @Cacheable(value = "wallets", key = "#userId")
    public BigDecimal getBalance(Long userId) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return wallet.getBalance();
    }
}
