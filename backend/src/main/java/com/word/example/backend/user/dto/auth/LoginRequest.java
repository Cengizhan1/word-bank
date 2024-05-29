package com.word.example.backend.user.dto.auth;

public record LoginRequest(
        String username,
        String password
){
}