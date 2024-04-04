package com.example.keycloakspringboot.service.impl;

import com.example.keycloakspringboot.dto.AuthenticationRequest;
import com.example.keycloakspringboot.dto.AuthenticationResponse;
import com.example.keycloakspringboot.dto.RefreshRequest;
import com.example.keycloakspringboot.service.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("client_id", "spring-boot-rest-api");
        map.add("username", "admin");
        map.add("password", "admin");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8282/realms/myrealm/protocol/openid-connect/token", request, String.class);
        try {
            AuthenticationResponse authenticationResponse = objectMapper.readValue(response.getBody(), AuthenticationResponse.class);
            return authenticationResponse;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthenticationResponse refresh(RefreshRequest refreshRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("client_id", "spring-boot-rest-api");
        map.add("refresh_token", refreshRequest.getRefreshToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8282/realms/myrealm/protocol/openid-connect/token", request, String.class);
        try {
            AuthenticationResponse authenticationResponse = objectMapper.readValue(response.getBody(), AuthenticationResponse.class);
            return authenticationResponse;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
