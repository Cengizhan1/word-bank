package com.word.example.backend.user.dto;

public record UserContactInformationRequest(
    String phone,
    Boolean phonePermission,
    Boolean emailPermission
) {
}
