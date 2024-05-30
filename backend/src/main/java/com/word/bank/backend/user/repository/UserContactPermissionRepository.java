package com.word.bank.backend.user.repository;

import com.word.bank.backend.user.model.User;
import com.word.bank.backend.user.model.UserContactPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserContactPermissionRepository extends JpaRepository<UserContactPermission, Long> {

    Optional<UserContactPermission> findByUser(User user);
}
