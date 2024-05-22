package com.word.example.backend.user.controller;

import com.word.example.backend.user.dto.UserContactInformationRequest;
import com.word.example.backend.user.service.UserContactInformationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/user-contact-information")
public class UserContactInformationController {

    private final UserContactInformationService service;

    public UserContactInformationController(UserContactInformationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> saveUserContactInformation(@Valid @RequestBody UserContactInformationRequest request) {
        service.create(request);
        return ResponseEntity.ok().build();
    }
}
