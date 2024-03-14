package com.n11.userservice.dto.request;

import com.n11.userservice.model.User;
import com.n11.userservice.util.enums.Score;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Created By Mustafa Aykurt
 * Date:12.03.2024
 * Time:00:02
 */

public record UserReviewSaveRequest(

        @NotBlank
        String comment,
        @NotBlank
        String score,
        @NotBlank
        String restaurantId,
        @NotNull
        Long userId

) {
}
