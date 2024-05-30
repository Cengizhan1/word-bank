package com.word.bank.backend.user.dto.userInformation;

import com.word.bank.backend.user.model.UserInformation;
import com.word.bank.backend.user.model.enums.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UserInformationDto(
        Gender gender,
        Integer age,
        String job,
        Double salary,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate
) {
        public static UserInformationDto convert(UserInformation userInformation) {
            return new UserInformationDto(
                    userInformation.getGender(),
                    userInformation.getAge(),
                    userInformation.getJob(),
                    userInformation.getSalary(),
                    userInformation.getBirthDate()
            );
        }
}
