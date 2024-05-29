package com.word.example.backend.user.repository;

import com.word.example.backend.user.model.User;
import com.word.example.backend.user.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
    Optional<UserInformation> findByUser(User user);
}
