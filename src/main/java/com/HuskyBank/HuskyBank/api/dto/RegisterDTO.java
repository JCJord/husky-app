package com.HuskyBank.HuskyBank.api.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class RegisterDTO {
    private String email;
    private String password;
    private BigInteger cpf;
    private String name;
}
