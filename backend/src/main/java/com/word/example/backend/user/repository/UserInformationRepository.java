package com.word.example.backend.user.repository;

import com.word.example.backend.user.model.User;
import com.word.example.backend.user.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
    Boolean existsByUser(User user);
}
