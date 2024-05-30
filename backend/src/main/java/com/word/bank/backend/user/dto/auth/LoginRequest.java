package com.word.bank.backend.user.dto.auth;

public record LoginRequest(
        String username,
        String password
){
}