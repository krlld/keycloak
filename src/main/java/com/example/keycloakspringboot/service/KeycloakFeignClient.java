package com.example.keycloakspringboot.service;

import com.example.keycloakspringboot.dto.AuthenticationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "keycloak-service", url = "${keycloak.token-uri}")
public interface KeycloakFeignClient {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AuthenticationResponse authenticate(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AuthenticationResponse refreshToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("refresh_token") String refreshToken
    );

}