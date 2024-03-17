package com.n11.userservice.dto.request;


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
