package com.example.keycloakspringboot.service;

import com.example.keycloakspringboot.dto.AuthenticationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "keycloak-service", url = "${keycloak.token-uri}")
public interface KeycloakFeignClient {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AuthenticationResponse authenticate(
            @RequestBody MultiValueMap<String, String> requestParams
    );

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AuthenticationResponse refreshToken(
            @RequestBody MultiValueMap<String, String> requestParams
    );

}