package com.janisar.service;

import com.janisar.domain.VerificationType;
import com.janisar.model.User;
import com.janisar.model.VerificationCode;

public interface VerificationCodeService {
    VerificationCode sendVerificationCode(User user , VerificationType verificationType);

    VerificationCode getVerificationCodeById(Long id) throws Exception;

    VerificationCode getVerificationCodeByUser(Long userId);

    void deleteVerificationCodeById(VerificationCode verificationCode);
}
