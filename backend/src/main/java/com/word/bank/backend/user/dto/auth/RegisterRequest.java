package com.word.bank.backend.user.dto.auth;


public record RegisterRequest(
        String name,
        String surname,
        String username,
        String password,
        String email,
        String phone
){}