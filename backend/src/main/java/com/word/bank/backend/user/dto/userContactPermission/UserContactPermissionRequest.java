package com.word.bank.backend.user.dto.userContactPermission;

public record UserContactPermissionRequest(
        Boolean phonePermission,
        Boolean emailPermission
) {
}
