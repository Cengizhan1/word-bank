package com.word.example.backend.user.service;

import com.word.example.backend.user.dto.UserInformationRequest;
import com.word.example.backend.user.model.User;
import com.word.example.backend.user.model.UserInformation;
import com.word.example.backend.user.repository.UserInformationRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService {

    private final UserInformationRepository repository;
    private final UserService userService;

    public UserInformationService(UserInformationRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public void create(UserInformationRequest request) {
        User user = userService.getAuthenticatedUser();
        checkUserInformation(user);
        UserInformation userInformation = UserInformation.builder()
                .gender(request.gender())
                .age(request.age())
                .job(request.job())
                .salary(request.salary())
                .birthDate(request.birthDate())
                .user(user)
                .build();

        repository.save(userInformation);
    }

    private void checkUserInformation(User user) {
        if (repository.existsByUser(user)) {
            throw new IllegalStateException("User information already exists");
        }
    }
}
