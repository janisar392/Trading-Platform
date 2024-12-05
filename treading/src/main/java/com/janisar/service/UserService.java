package com.janisar.service;

import com.janisar.domain.VerificationType;
import com.janisar.model.User;

public interface UserService {

    public User findUserProfileByJwt(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
    public User findUserById(Long userId) throws Exception;

    public User enabletwoFactorAuthentication(
            VerificationType verificationType,
            String sendTo,
            User user);

    User updatePassword(User user , String newPassword);
}
