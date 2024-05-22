package com.word.example.backend.user.dto;


import com.word.example.backend.user.model.User;

public record UserDto(
        String name,
        String surname,
        String username

) {
    public static UserDto convert(User user) {
        return new UserDto(
                user.getName(),
                user.getSurname(),
                user.getUsername());
    }
}