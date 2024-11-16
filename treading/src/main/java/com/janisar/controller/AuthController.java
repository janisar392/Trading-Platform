package com.janisar.controller;

import com.janisar.config.JwtProvider;
import com.janisar.model.TwoFactorOTP;
import com.janisar.model.User;
import com.janisar.repository.UserRepository;
import com.janisar.response.AuthResponse;
import com.janisar.service.CustomerUserDetailsService;
import com.janisar.service.EmailService;
import com.janisar.service.TwoFactorOtpService;
import com.janisar.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TwoFactorOtpService twoFactorOtpService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {


        User isEmailExist = userRepository.findByEmail(user.getEmail());

        if(isEmailExist!=null){
            throw new Exception("email is already Exist and used in Another Account");
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFullname(user.getFullname());

        User saveUser = userRepository.save(newUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("register success");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception {


        String userName=user.getEmail();
        String password = user.getPassword();

        Authentication auth = authenticate(userName,password);

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        User authuser =userRepository.findByEmail(userName);

        if(user.getTwofactorAuth().isEnabled()){

            AuthResponse res = new AuthResponse();
            res.setMessage("Two factor Auth is enabled");
            res.setTwoFactorAuthEnabled(true);
            String otp = OtpUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP = twoFactorOtpService.findByUser(authuser.getId());
            if(oldTwoFactorOTP!=null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);
            }
            TwoFactorOTP newTwoFactorOTP= twoFactorOtpService.createTwoFactorOtp(
                    authuser,otp,jwt);

            emailService.sendVerificationOtpEmail(userName,otp);

            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

        }

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("login Successfully");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String userName, String password) {

        UserDetails userDetails= customerUserDetailsService.loadUserByUsername(userName);

        if (userDetails==null){
            throw new BadCredentialsException("Invalid username");
        }
        if(!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());

    }

    public ResponseEntity<AuthResponse> verifySigninOtp(
            @PathVariable String otp,
            @RequestParam String id) throws Exception{
        TwoFactorOTP twoFactorOTP=twoFactorOtpService.findById(id);

        if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP,otp)){
            AuthResponse res = new AuthResponse();
            res.setMessage("Two factor authentication verified");
            res.setTwoFactorAuthEnabled(true);
            res.setJwt(twoFactorOTP.getJwt());
            return new ResponseEntity<>(res,HttpStatus.OK);
        }
        throw new Exception("invalid OTP");
    }

}
