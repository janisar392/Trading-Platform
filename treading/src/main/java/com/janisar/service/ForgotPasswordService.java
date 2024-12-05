package com.janisar.service;

import com.janisar.domain.VerificationType;
import com.janisar.model.ForgotPasswordToken;
import com.janisar.model.User;

public interface ForgotPasswordService {

    ForgotPasswordToken createToken(User user ,
                                    String id ,
                                    String otp ,
                                    VerificationType verificationType ,
                                    String sendTo);

    ForgotPasswordToken findById(String id);

    ForgotPasswordToken findByUser(long userId);

    void deleteToken(ForgotPasswordToken token);
}
