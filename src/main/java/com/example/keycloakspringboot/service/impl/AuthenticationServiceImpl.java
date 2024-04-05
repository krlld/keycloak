package com.example.keycloakspringboot.service.impl;

import com.example.keycloakspringboot.dto.AuthenticationRequest;
import com.example.keycloakspringboot.dto.AuthenticationResponse;
import com.example.keycloakspringboot.dto.RefreshRequest;
import com.example.keycloakspringboot.service.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final String tokenUri;

    private final String clientId;

    public AuthenticationServiceImpl(
            RestTemplate restTemplate,
            ObjectMapper objectMapper,
            @Value("${keycloak.token-uri}") String tokenUri,
            @Value("${keycloak.client-id}") String clientId) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
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

        ResponseEntity<String> response = sendRequestForToken(requestParams);
        return parseResponse(response);
    }

    @Override
    public AuthenticationResponse refresh(RefreshRequest refreshRequest) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("grant_type", "refresh_token");
        requestParams.add("client_id", clientId);
        requestParams.add("refresh_token", refreshRequest.getRefreshToken());

        ResponseEntity<String> response = sendRequestForToken(requestParams);
        return parseResponse(response);
    }

    private ResponseEntity<String> sendRequestForToken(MultiValueMap<String, String> requestParams) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestParams, headers);
        return restTemplate.postForEntity(tokenUri, request, String.class);
    }

    private AuthenticationResponse parseResponse(ResponseEntity<String> response) {
        try {
            return objectMapper.readValue(response.getBody(), AuthenticationResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
