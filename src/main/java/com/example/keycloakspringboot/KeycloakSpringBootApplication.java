package com.example.keycloakspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KeycloakSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeycloakSpringBootApplication.class, args);
    }

}
