package com.n11.userservice.dto.client;

/**
 * Created By Mustafa Aykurt
 * Date:17.03.2024
 * Time:03:27
 */

public record RestaurantRecommendationDto(
        String id,

        String name,

        String address,

        String city,

        String district,

        String country,

        Double latitude,

        Double longitude,

        Double rating,

        Double distance,

        Double totalWeightedScore
) {
}
