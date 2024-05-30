package com.word.bank.backend.user.dto;

public record UserProfileImage(
        byte[] profile
) {

    public static UserProfileImage convert(byte[] profile) {
        return new UserProfileImage(profile);
    }
}
