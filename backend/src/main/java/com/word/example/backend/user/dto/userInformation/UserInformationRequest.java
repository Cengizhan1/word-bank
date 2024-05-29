package com.word.example.backend.user.dto.userInformation;

import com.word.example.backend.user.model.enums.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UserInformationRequest(
        Gender gender,
        Integer age,
        String job,
        Double salary,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate
) {
}
