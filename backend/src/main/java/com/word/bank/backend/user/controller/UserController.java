package com.word.bank.backend.user.controller;


import com.word.bank.backend.user.dto.UserDto;
import com.word.bank.backend.user.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<UserDto> show() {
        return ResponseEntity.ok(service.show());
    }

    @PostMapping("/upload-profile-image")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("profileImage") MultipartFile profileImage) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(service.uploadProfileImage(profileImage));
    }

    @GetMapping("/get-profile-image")
    public ResponseEntity<?> getProfileImage() {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(service.getProfileImage());
    }

    @GetMapping("/verify")
    public ResponseEntity<Void> verifyEmail(@Param("code") String code) {
        service.verify(code);
        return ResponseEntity.ok().build();
    }

}
