package com.example.keycloakspringboot.dto;

import lombok.Data;

@Data
public class RefreshRequest {

    private String refreshToken;
}
