package com.word.bank.backend.user.repository;

import com.word.bank.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    Optional<User> findByVerificationCode(String code);

}
