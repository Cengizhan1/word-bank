package com.word.example.backend.user.dto;

public record LoginRequest(
        String username,
        String password
){
}