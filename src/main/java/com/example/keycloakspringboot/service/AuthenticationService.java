package com.example.keycloakspringboot.service;

import com.example.keycloakspringboot.dto.AuthenticationRequest;
import com.example.keycloakspringboot.dto.AuthenticationResponse;
import com.example.keycloakspringboot.dto.RefreshRequest;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    AuthenticationResponse refresh(RefreshRequest refreshRequest);
}
