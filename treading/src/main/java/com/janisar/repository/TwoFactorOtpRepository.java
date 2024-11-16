package com.janisar.repository;

import com.janisar.model.TwoFactorOTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwoFactorOtpRepository extends JpaRepository<TwoFactorOTP ,String> {
    TwoFactorOTP findByUserId(Long userId);
}
