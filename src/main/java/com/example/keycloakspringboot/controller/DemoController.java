package com.example.keycloakspringboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @PreAuthorize("hasRole('CLIENT_USER')")
    @GetMapping("/user")
    public String userResource() {
        return "Some user info";
    }

    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    @GetMapping("/admin")
    public String adminResource() {
        return "Some admin info";
    }
}
