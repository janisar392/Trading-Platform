package com.janisar.service;

import com.janisar.config.JwtProvider;
import com.janisar.domain.VerificationType;
import com.janisar.model.TwofactorAuth;
import com.janisar.model.User;
import com.janisar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new Exception("User Not found");
        }
        return user.get();
    }

    @Override
    public User enabletwoFactorAuthentication(VerificationType verificationType, String sendTo, User user) {
        TwofactorAuth twofactorAuth = new TwofactorAuth();
        twofactorAuth.setEnabled(true);
        twofactorAuth.setSendTo(verificationType);

        user.setTwofactorAuth(twofactorAuth);
        return userRepository.save(user);

    }


    @Override
    public User updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return userRepository.save(user);
    }
}
