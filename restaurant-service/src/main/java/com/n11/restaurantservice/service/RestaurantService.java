package com.n11.restaurantservice.service;

import com.n11.restaurantservice.dto.request.RestaurantSaveRequest;
import com.n11.restaurantservice.dto.request.RestaurantUpdateRequest;
import com.n11.restaurantservice.dto.response.RestaurantDto;
import com.n11.restaurantservice.repository.RestaurantRepository;
import com.n11.restaurantservice.util.mapper.RestaurantMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created By Mustafa Aykurt
 * Date:15.03.2024
 * Time:00:34
 */

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;
    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public void saveRestaurant(RestaurantSaveRequest request) {
        var restaurant = restaurantMapper.convertToRestaurant(request);
        restaurant.setCreateDate(LocalDateTime.now());
        restaurant.setUpdateDate(LocalDateTime.now());
        restaurantRepository.save(restaurant);
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
