package com.word.example.backend.user.service;

import com.word.example.backend.user.dto.UserDto;
import com.word.example.backend.user.model.User;
import com.word.example.backend.user.repository.UserRepository;
import com.word.example.backend.user.util.ImageUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
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
        try {
            return ImageUtils.decompressImage(user.getProfileImage());
        } catch (DataFormatException | IOException exception) {
            throw new RuntimeException("Failed to decompress image", exception);
        }
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
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
