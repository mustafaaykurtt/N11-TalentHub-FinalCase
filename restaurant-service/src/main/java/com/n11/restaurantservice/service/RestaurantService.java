package com.n11.restaurantservice.service;

import com.n11.restaurantservice.dto.request.RestaurantSaveRequest;
import com.n11.restaurantservice.dto.request.RestaurantUpdateRequest;
import com.n11.restaurantservice.dto.response.RestaurantDto;
import com.n11.restaurantservice.exception.NotFoundException;
import com.n11.restaurantservice.model.Restaurant;
import com.n11.restaurantservice.repository.RestaurantRepository;
import com.n11.restaurantservice.util.mapper.RestaurantMapper;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        var restaurantInDB = getRestaurantById(request.id());
        restaurantMapper.updateRestaurantFromDTO(request, restaurantInDB);
        restaurantInDB.setUpdateDate(LocalDateTime.now());
        restaurantRepository.save(restaurantInDB);
        return restaurantMapper.convertToRestaurantDto(restaurantInDB);
    }

    private Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    public List<RestaurantDto> getAllRestaurants() {
        var restaurants = restaurantRepository.findAll();
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        restaurants.forEach(restaurant -> {
            restaurantDtos.add(restaurantMapper.convertToRestaurantDto(restaurant));
        });
        return restaurantDtos;
    }


    public void deleteRestaurant(String id) {
        var restaurantInDB = getRestaurantById(id);
        restaurantRepository.delete(restaurantInDB);
    }

    public void findRestaurantsNearUser(Double latitude, Double longitude, Integer distance) {
        restaurantRepository.findByLocationNear(new Point(latitude,longitude),new Distance(distance, Metrics.KILOMETERS));
    }
}
