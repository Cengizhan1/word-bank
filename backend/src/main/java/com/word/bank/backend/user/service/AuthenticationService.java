package com.word.bank.backend.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.word.bank.backend.user.dto.auth.AuthenticationResponse;
import com.word.bank.backend.user.dto.auth.LoginRequest;
import com.word.bank.backend.user.dto.auth.RegisterRequest;
import com.word.bank.backend.user.exception.UsernameAlreadyExistsException;
import com.word.bank.backend.user.model.Token;
import com.word.bank.backend.user.model.User;
import com.word.bank.backend.user.model.enums.Role;
import com.word.bank.backend.user.repository.TokenRepository;
import com.word.bank.backend.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender mailSender;

    private String generateEmailVerificationCode() {
        Random random = new Random();
        int verificationCode = 10000 + random.nextInt(90000);
        return Integer.toString(verificationCode);
    }

    public AuthenticationResponse register(RegisterRequest request,String siteURL) throws MessagingException, UnsupportedEncodingException {

        if (repository.existsByUsername(request.username())) {
            throw new UsernameAlreadyExistsException("Username already exists for this user");
        }
        if (repository.existsByEmail(request.email())) {
            throw new UsernameAlreadyExistsException("Email already exists for this user");
        }
        if (repository.existsByPhone(request.phone())) {
            throw new UsernameAlreadyExistsException("Phone already exists for this user");
        }
        var user = User.builder()
                .name(request.name())
                .surname(request.surname())
                .username(request.username())
                .email(request.email())
                .phone(request.phone())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ROLE_USER)
                .build();

        String emailCode = generateEmailVerificationCode();
        user.setVerificationCode(emailCode);
        user.setVerificationCodeSentAt(LocalDateTime.now());
        user.setEnabled(false);

        sendVerificationEmail(user,emailCode);

        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .firstname(savedUser.getName())
                .build();
    }

    public AuthenticationResponse authenticate(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var user = repository.findByUsername(request.username())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .firstname(user.getName())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void sendVerificationEmail(User user, String code)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "cengizhany.cy@gmail.com";
        String senderName = "Word Bank";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "You have successfully registered. Your verification code is <b>"+code+"</b><br>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getName() + " " + user.getSurname());

        helper.setText(content, true);

        mailSender.send(message);

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            var user = this.repository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
