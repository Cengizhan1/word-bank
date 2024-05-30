package com.word.bank.backend.user.dto.userContactPermission;

import com.word.bank.backend.user.model.UserContactPermission;

public record UserContactPermissionDto(
        Boolean phonePermission,
        Boolean emailPermission
) {

    public static UserContactPermissionDto convert(UserContactPermission permission) {
        return new UserContactPermissionDto(
                permission.getPhonePermission(),
                permission.getEmailPermission());
    }
}
