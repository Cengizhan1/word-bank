package com.word.example.backend.user.repository;

import com.word.example.backend.user.model.User;
import com.word.example.backend.user.model.UserContactPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserContactPermissionRepository extends JpaRepository<UserContactPermission, Long> {

    Optional<UserContactPermission> findByUser(User user);
}
