package com.janisar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.janisar.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullname;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Embedded
    private TwofactorAuth twofactorAuth = new TwofactorAuth();

    private USER_ROLE role =USER_ROLE.ROLE_CUSTOMER;




}
