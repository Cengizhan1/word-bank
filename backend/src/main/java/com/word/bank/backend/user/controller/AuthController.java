package com.word.bank.backend.user.controller;

import com.word.bank.backend.user.dto.auth.AuthenticationResponse;
import com.word.bank.backend.user.dto.auth.LoginRequest;
import com.word.bank.backend.user.dto.auth.RegisterRequest;
import com.word.bank.backend.user.service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    private final AuthenticationService service;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request, HttpServletRequest httpServletRequest
    ) throws MessagingException, UnsupportedEncodingException {
        try {
            return ResponseEntity.ok(service.register(request,getSiteURL(httpServletRequest)));
        } catch (Exception e) {
            logger.error("register method threw an exception", e);
            throw e;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody LoginRequest request
    ) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (Exception e) {
            logger.error("authenticate method threw an exception", e);
            throw e;
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
