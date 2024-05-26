package com.word.example.backend.user.dto;


public record RegisterRequest(
        String name,
        String surname,
        String username,
        String password,
        String email
){}