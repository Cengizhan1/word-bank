package com.word.example.backend.user.repository;

import com.word.example.backend.user.model.User;
import com.word.example.backend.user.model.UserContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactInformationRepository extends JpaRepository<UserContactInformation, Long> {

    Boolean existsByUser(User user);
}
