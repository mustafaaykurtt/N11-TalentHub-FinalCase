package com.n11.restaurantservice.dto.request;

/**
 * Created By Mustafa Aykurt
 * Date:15.03.2024
 * Time:00:30
 */

public record RestaurantSaveRequest(

        String name,

        String address,

        String city,

        String district,

        String country,

        Double latitude,

        Double longitude
) {
}
