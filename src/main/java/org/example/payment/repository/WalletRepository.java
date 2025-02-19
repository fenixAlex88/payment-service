package org.example.payment.repository;

import org.example.payment.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT w FROM Wallet w WHERE w.userId = :userId")
    Optional<Wallet> findByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(w) > 0 FROM Wallet w WHERE w.userId = :userId")
    boolean existsByUserId(@Param("userId") Long userId);
}