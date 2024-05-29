package com.word.example.backend.user.controller;

import com.word.example.backend.user.dto.userInformation.UserInformationDto;
import com.word.example.backend.user.dto.userInformation.UserInformationRequest;
import com.word.example.backend.user.service.UserInformationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user-information")
public class UserInformationController {

    private final UserInformationService service;

    public UserInformationController(UserInformationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserInformationDto> save(@Valid @RequestBody UserInformationRequest request) {
        return  ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<UserInformationDto> show() {
        return  ResponseEntity.ok(service.show());
    }
}
