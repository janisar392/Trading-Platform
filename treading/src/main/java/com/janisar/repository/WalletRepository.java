package com.janisar.repository;

import com.janisar.model.User;
import com.janisar.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet , Long> {

    Wallet findByUserId(Long userId);
}
