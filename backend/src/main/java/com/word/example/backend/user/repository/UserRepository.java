package com.word.example.backend.user.repository;

import com.word.example.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    User findByVerificationCode(String code);

}
