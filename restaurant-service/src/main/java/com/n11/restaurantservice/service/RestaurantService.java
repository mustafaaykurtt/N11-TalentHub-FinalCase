package com.n11.restaurantservice.service;

import com.n11.restaurantservice.dto.request.RestaurantSaveRequest;
import com.n11.restaurantservice.dto.request.RestaurantUpdateRequest;
import com.n11.restaurantservice.dto.response.RestaurantDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Mustafa Aykurt
 * Date:14.03.2024
 * Time:03:47
 */
@Service
public class RestaurantService {
    public void saveRestaurant(RestaurantSaveRequest request) {
    }

    public RestaurantDto updateRestaurant(RestaurantUpdateRequest request) {
        return null;
    }

    public List<RestaurantDto> getAllRestaurants() {
        return null;
    }

    public void deleteRestaurant(String id) {
    }
}
