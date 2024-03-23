package com.HuskyBank.HuskyBank.api.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private String email;
    private Long userId;

    public AuthResponseDTO(String accessToken, String email, Long userId) {
        this.accessToken = accessToken;
        this.email = email;
        this.userId = userId;
    }
}
