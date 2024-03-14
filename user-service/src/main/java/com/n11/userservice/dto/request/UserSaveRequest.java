package com.n11.userservice.dto.request;

import com.n11.userservice.annotation.UniqueEmail;
import com.n11.userservice.util.enums.Gender;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Created By Mustafa Aykurt
 * Date:9.03.2024
 * Time:22:37
 */

public record UserSaveRequest(
        @NotBlank(message = "{n11.constraint.name.notBlank}")
        @Size(min = 2, max = 60, message = "{n11.constraint.name.size}")
        String name,

        @NotBlank(message = "{n11.constraint.surname.notBlank}")
        @Size(min = 2, max = 60, message = "{n11.constraint.surname.size}")
        String surname,

        @NotNull(message = "{n11.constraint.birthDate.notBlank}")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate birthDate,

        @NotBlank(message = "{n11.constraint.email.notBlank}")
        @Email(message = "{n11.constraint.email.invalid}")
        @UniqueEmail
        String email,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{n11.constraint.password.pattern}")
        @Size(min = 5, max = 255, message = "{n11.constraint.password.size}")
        String password,

        @NotNull(message = "{n11.constraint.latitude.notBlank}")
        @DecimalMin(value = "-90", message = "{n11.constraint.latitude.min}")
        @DecimalMax(value = "90", message = "{n11.constraint.latitude.max}")
        Double latitude,

        @NotNull(message = "{n11.constraint.longitude.notBlank}")
        @DecimalMin(value = "-180", message = "{n11.constraint.longitude.min}")
        @DecimalMax(value = "180", message = "{n11.constraint.longitude.max}")
        Double longitude,

        Gender gender

) {
}
