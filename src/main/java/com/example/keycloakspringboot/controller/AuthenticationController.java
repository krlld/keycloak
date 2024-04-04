package com.example.keycloakspringboot.controller;

import com.example.keycloakspringboot.dto.AuthenticationRequest;
import com.example.keycloakspringboot.dto.AuthenticationResponse;
import com.example.keycloakspringboot.dto.RefreshRequest;
import com.example.keycloakspringboot.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refresh(@RequestBody RefreshRequest refreshRequest) {
        return authenticationService.refresh(refreshRequest);
    }
}
