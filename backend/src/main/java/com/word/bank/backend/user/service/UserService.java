package com.word.bank.backend.user.service;

import com.word.bank.backend.user.dto.UserDto;
import com.word.bank.backend.user.exception.EmailVerificationException;
import com.word.bank.backend.user.model.User;
import com.word.bank.backend.user.repository.UserRepository;
import com.word.bank.backend.user.util.ImageUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.zip.DataFormatException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDto show() {
        return UserDto.convert(getAuthenticatedUser());
    }

    public byte[] uploadProfileImage(MultipartFile profile) throws IOException {
        User user = getAuthenticatedUser();
        user.setProfileImage(ImageUtils.compressImage(profile.getBytes()));
        userRepository.save(user);
        return decompressImage(user.getProfileImage());
    }

    public byte[] getProfileImage() {
        User user = getAuthenticatedUser();
        return decompressImage(user.getProfileImage());
    }

    public void verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode).orElseThrow(
                () -> new EmailVerificationException("Verification code is wrong")
        );
        if (user.isEnabled()) {
            throw new EmailVerificationException("User is already verified");
        }
        if (user.getVerificationCode().equals(verificationCode) && user.getVerificationCodeSentAt()
                .plusMinutes(10).isAfter(LocalDateTime.now())) {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
        }else {
            throw new EmailVerificationException("Verification code is wrong or expired");
        }
    }

    private byte[] decompressImage(byte[] image) {
        try {
            return ImageUtils.decompressImage(image);
        } catch (DataFormatException | IOException exception) {
            throw new RuntimeException("Failed to decompress image", exception);
        }
    }

    protected User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName()).orElseThrow((
                () -> new UsernameNotFoundException("Authenticated user not found")));
    }

    private User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    protected User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}
