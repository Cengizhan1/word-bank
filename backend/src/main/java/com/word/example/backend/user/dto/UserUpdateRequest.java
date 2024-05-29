package com.word.example.backend.user.dto;

public record UserUpdateRequest(
        String name,
        String username,
        String surname,
        String password,
        String email,
        String phone
){
}