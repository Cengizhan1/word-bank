package com.word.example.backend.user.service;

import com.word.example.backend.user.dto.userInformation.UserInformationDto;
import com.word.example.backend.user.dto.userInformation.UserInformationRequest;
import com.word.example.backend.user.model.User;
import com.word.example.backend.user.model.UserInformation;
import com.word.example.backend.user.repository.UserInformationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService {

    private final UserInformationRepository repository;
    private final UserService userService;

    public UserInformationService(UserInformationRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public UserInformationDto save(UserInformationRequest request) {
        User user = userService.getAuthenticatedUser();
        UserInformation userInformation= repository.findByUser(user).orElseGet(UserInformation::new);
        userInformation.setGender(request.gender());
        userInformation.setAge(request.age());
        userInformation.setJob(request.job());
        userInformation.setSalary(request.salary());
        userInformation.setBirthDate(request.birthDate());
        userInformation.setUser(user);
        repository.save(userInformation);
        return UserInformationDto.convert(userInformation);
    }

    public UserInformationDto show() {
        User user = userService.getAuthenticatedUser();
        return repository.findByUser(user).map(UserInformationDto::convert).orElseThrow(
                () -> new EntityNotFoundException("User information not found")
        );
    }
}
