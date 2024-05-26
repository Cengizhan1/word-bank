package com.word.example.backend.user.service;

import com.word.example.backend.user.dto.UserContactInformationRequest;
import com.word.example.backend.user.model.User;
import com.word.example.backend.user.model.UserContactInformation;
import com.word.example.backend.user.repository.UserContactInformationRepository;
import org.springframework.stereotype.Service;

@Service
public class UserContactInformationService {

    private final UserContactInformationRepository repository;
    private final UserService userService;

    public UserContactInformationService(UserContactInformationRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public void create(UserContactInformationRequest request) {
        User user = userService.getAuthenticatedUser();
        checkUserContactInformation(user);
        UserContactInformation userContactInformation =UserContactInformation.builder()
                .phone(request.phone())
                .phonePermission(request.phonePermission())
                .emailPermission(request.emailPermission())
                .user(user)
                .build();

        repository.save(userContactInformation);
    }

    private void checkUserContactInformation(User user) {
        if (repository.existsByUser(user)) {
            throw new IllegalStateException("User contact information already exists");
        }
    }
}
