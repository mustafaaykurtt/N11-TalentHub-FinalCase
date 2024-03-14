package com.n11.userservice.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Created By Mustafa Aykurt
 * Date:9.03.2024
 * Time:22:38
 */

public record UserReviewDto(

        Long id,
        String score,
        String restaurantId,
        Long userId
) {
}
