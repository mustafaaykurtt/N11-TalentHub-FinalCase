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

public record UserUpdateRequest(

        @NotNull
        Long id,

        @NotBlank(message = "{fundeep.constraint.firstName.notBlank}")
        @Size(min = 2, max = 60, message = "{fundeep.constraint.firstName.size}")
        String name,

        @NotBlank(message = "{fundeep.constraint.lastName.notBlank}")
        @Size(min = 2, max = 60, message = "{fundeep.constraint.lastName.size}")
        String surname,

        @DateTimeFormat
        @Past
        LocalDate birthDate,

        @NotBlank(message = "{n11.constraint.email.notBlank}")
        @Email(message = "{n11.constraint.email.invalid}")
        @UniqueEmail
        String email,

        @NotBlank(message = "{n11.constraint.latitude.notBlank}")
        @DecimalMin(value = "-90", message = "{n11.constraint.latitude.min}")
        @DecimalMax(value = "90", message = "{n11.constraint.latitude.max}")
        Double latitude,

        @NotBlank(message = "{n11.constraint.longitude.notBlank}")
        @DecimalMin(value = "-180", message = "{n11.constraint.longitude.min}")
        @DecimalMax(value = "180", message = "{n11.constraint.longitude.max}")
        Double longitude,

        Gender gender
) {
}
