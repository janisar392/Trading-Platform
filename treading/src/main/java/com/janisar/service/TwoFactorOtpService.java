package com.janisar.service;

import com.janisar.model.TwoFactorOTP;
import com.janisar.model.User;

public interface TwoFactorOtpService {

    TwoFactorOTP createTwoFactorOtp(User user , String otp , String jwt);

    TwoFactorOTP findByUser(Long userId);

    TwoFactorOTP findById(String id);

    boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP , String otp);

    void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP);
}
