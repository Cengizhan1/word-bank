package com.word.example.backend.user.controller;

import com.word.example.backend.user.dto.userContactPermission.UserContactPermissionDto;
import com.word.example.backend.user.dto.userContactPermission.UserContactPermissionRequest;
import com.word.example.backend.user.service.UserContactPermissionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user-contact-permission")
public class UserContactPermissionController {

    private final UserContactPermissionService service;

    public UserContactPermissionController(UserContactPermissionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> update(@Valid @RequestBody UserContactPermissionRequest request) {
        service.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserContactPermissionDto> show() {
        return  ResponseEntity.ok(service.show());
    }
}
