package com.example.keycloakspringboot.exception;

public class NotFountException extends RuntimeException {
    public NotFountException(String message) {
        super(message);
    }
}