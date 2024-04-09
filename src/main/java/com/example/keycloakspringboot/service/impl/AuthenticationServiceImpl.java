package com.example.keycloakspringboot.service.impl;

import com.example.keycloakspringboot.dto.AuthenticationRequest;
import com.example.keycloakspringboot.dto.AuthenticationResponse;
import com.example.keycloakspringboot.dto.RefreshRequest;
import com.example.keycloakspringboot.service.AuthenticationService;
import com.example.keycloakspringboot.util.RequestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RequestUtils requestUtils;

    private final String tokenUri;

    private final String clientId;

    public AuthenticationServiceImpl(
            RequestUtils requestUtils,
            @Value("${keycloak.token-uri}") String tokenUri,
            @Value("${keycloak.client-id}") String clientId) {
        this.requestUtils = requestUtils;
        this.tokenUri = tokenUri;
        this.clientId = clientId;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("grant_type", "password");
        requestParams.add("client_id", clientId);
        requestParams.add("username", authenticationRequest.getUsername());
        requestParams.add("password", authenticationRequest.getPassword());

        ResponseEntity<String> response = requestUtils.sendRequest(
                tokenUri,
                MediaType.APPLICATION_FORM_URLENCODED,
                requestParams
        );
        return requestUtils.parseResponse(response, AuthenticationResponse.class);
    }

    @Override
    public AuthenticationResponse refresh(RefreshRequest refreshRequest) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("grant_type", "refresh_token");
        requestParams.add("client_id", clientId);
        requestParams.add("refresh_token", refreshRequest.getRefreshToken());

        ResponseEntity<String> response = requestUtils.sendRequest(
                tokenUri,
                MediaType.APPLICATION_FORM_URLENCODED,
                requestParams
        );
        return requestUtils.parseResponse(response, AuthenticationResponse.class);
    }
}
