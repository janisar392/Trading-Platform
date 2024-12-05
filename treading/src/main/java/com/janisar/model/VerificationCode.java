package com.janisar.model;

import com.janisar.domain.VerificationType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String otp;
    @OneToOne
    private User user;

    private String email;

    private String mobile;

    private VerificationType verificationType;
}
