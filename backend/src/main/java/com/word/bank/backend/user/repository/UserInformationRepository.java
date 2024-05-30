package com.word.bank.backend.user.repository;

import com.word.bank.backend.user.model.User;
import com.word.bank.backend.user.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
    Optional<UserInformation> findByUser(User user);
}
