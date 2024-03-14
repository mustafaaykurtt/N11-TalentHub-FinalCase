package com.n11.userservice.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * Created By Mustafa Aykurt
 * Date:12.03.2024
 * Time:00:03
 */

public record UserReviewUpdateRequest(
        @NotNull
        Long id,
        String comment,
        String score
) {
}
