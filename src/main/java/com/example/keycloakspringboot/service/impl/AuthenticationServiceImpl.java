package com.example.keycloakspringboot.service.impl;

import com.example.keycloakspringboot.dto.AuthenticationRequest;
import com.example.keycloakspringboot.dto.AuthenticationResponse;
import com.example.keycloakspringboot.dto.RefreshRequest;
import com.example.keycloakspringboot.service.AuthenticationService;
import com.example.keycloakspringboot.service.KeycloakFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final KeycloakFeignClient keycloakFeignClient;

    private final String clientId;

    public AuthenticationServiceImpl(
            KeycloakFeignClient keycloakFeignClient,
            @Value("${keycloak.client-id}") String clientId) {
        this.keycloakFeignClient = keycloakFeignClient;
        this.clientId = clientId;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        return keycloakFeignClient.authenticate(
                "password",
                clientId,
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        );
    }

    @Override
    public AuthenticationResponse refresh(RefreshRequest refreshRequest) {
        return keycloakFeignClient.refreshToken(
                "refresh_token",
                clientId,
                refreshRequest.getRefreshToken()
        );
    }
}
