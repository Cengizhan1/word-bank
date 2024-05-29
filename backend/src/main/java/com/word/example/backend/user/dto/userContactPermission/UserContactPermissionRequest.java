package com.word.example.backend.user.dto.userContactPermission;

import com.word.example.backend.user.model.UserContactPermission;

public record UserContactPermissionRequest(
        Boolean phonePermission,
        Boolean emailPermission
) {
}
