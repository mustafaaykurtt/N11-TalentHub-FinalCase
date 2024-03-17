package com.n11.restaurantservice.service;

import com.n11.restaurantservice.dto.request.RestaurantSaveRequest;
import com.n11.restaurantservice.dto.request.RestaurantUpdateRequest;
import com.n11.restaurantservice.dto.response.RestaurantDto;
import com.n11.restaurantservice.dto.response.RestaurantRecommendationDto;
import com.n11.restaurantservice.exception.NotFoundException;
import com.n11.restaurantservice.model.Restaurant;
import com.n11.restaurantservice.repository.RestaurantRepository;
import com.n11.restaurantservice.util.Calculation;
import com.n11.restaurantservice.util.mapper.RestaurantMapper;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<RestaurantRecommendationDto> findRestaurantsNearUser(Double userLatitude, Double userLongitude, Integer distance) {
        final Double WEIGHT_RATING=0.7;
        final Double WEIGHT_DISTANCE=0.3;

        //Get restaurants within 10km from SOLR
        var restaurants = restaurantRepository.findByLocationNear(new Point(userLatitude, userLongitude), new Distance(distance, Metrics.KILOMETERS));

        return restaurants.stream()
                .map(restaurant -> {
                    //Get the restaurant's score and distance from the user
                    Double restaurantScore = restaurant.getRating();
                    Double restaurantDistance = Calculation.calculateDistance(userLatitude, userLongitude,
                            restaurant.getLocation().getX(), restaurant.getLocation().getY());

                    // Calculate scoring and distance weights
                    Double weightedScore = restaurantScore * WEIGHT_RATING;
                    Double weightedDistance = (WEIGHT_DISTANCE * (1 / restaurantDistance));

                    // Calculate the total score and create a restaurant DTO
                    Double totalWeightedScore = weightedScore + weightedDistance;
                    return new RestaurantRecommendationDto(
                            restaurant.getId(),
                            restaurant.getName(),
                            restaurant.getAddress(),
                            restaurant.getCity(),
                            restaurant.getDistrict(),
                            restaurant.getCountry(),
                            restaurant.getLocation().getX(),
                            restaurant.getLocation().getY(),
                            restaurant.getRating(),
                            restaurantDistance,
                            totalWeightedScore
                    );
                })
                .sorted(Comparator.comparingDouble(RestaurantRecommendationDto::totalWeightedScore).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public void deleteRestaurant(String id) {
        var restaurantInDB = getRestaurantById(id);
        restaurantRepository.delete(restaurantInDB);
    }


}
