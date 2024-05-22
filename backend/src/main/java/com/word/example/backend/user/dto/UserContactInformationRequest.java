package com.word.example.backend.user.dto;

public record UserContactInformationRequest(
    String phone,
    String email,
    Boolean phonePermission,
    Boolean emailPermission
) {
}
