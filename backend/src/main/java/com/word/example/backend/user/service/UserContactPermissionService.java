package com.word.example.backend.user.service;

import com.word.example.backend.user.dto.userContactPermission.UserContactPermissionDto;
import com.word.example.backend.user.dto.userContactPermission.UserContactPermissionRequest;
import com.word.example.backend.user.model.User;
import com.word.example.backend.user.model.UserContactPermission;
import com.word.example.backend.user.repository.UserContactPermissionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserContactPermissionService {

    private final UserContactPermissionRepository repository;
    private final UserService userService;

    public UserContactPermissionService(UserContactPermissionRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public void save(UserContactPermissionRequest request) {
        User user = userService.getAuthenticatedUser();
        UserContactPermission permission = repository.findByUser(user).orElseGet(UserContactPermission::new);
        permission.setPhonePermission(request.phonePermission());
        permission.setEmailPermission(request.emailPermission());
        permission.setUser(user);
        repository.save(permission);
    }

    public UserContactPermissionDto show() {
        User user = userService.getAuthenticatedUser();
        return repository.findByUser(user).map(UserContactPermissionDto::convert).orElseThrow(
                () -> new EntityNotFoundException("User contact permission not found")
        );
    }
}
