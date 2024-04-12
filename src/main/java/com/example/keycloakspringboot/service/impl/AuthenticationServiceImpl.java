package com.example.keycloakspringboot.service.impl;

import com.example.keycloakspringboot.dto.AuthenticationRequest;
import com.example.keycloakspringboot.dto.AuthenticationResponse;
import com.example.keycloakspringboot.dto.RefreshRequest;
import com.example.keycloakspringboot.service.AuthenticationService;
import com.example.keycloakspringboot.service.KeycloakFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("grant_type", "password");
        requestParams.add("client_id", clientId);
        requestParams.add("username", authenticationRequest.getUsername());
        requestParams.add("password", authenticationRequest.getPassword());

        return keycloakFeignClient.authenticate(requestParams);
    }

    @Override
    public AuthenticationResponse refresh(RefreshRequest refreshRequest) {

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("grant_type", "refresh_token");
        requestParams.add("client_id", clientId);
        requestParams.add("refresh_token", refreshRequest.getRefreshToken());

        return keycloakFeignClient.refreshToken(requestParams);
    }
}
