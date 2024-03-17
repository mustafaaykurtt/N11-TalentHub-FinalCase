package com.n11.userservice.client;

import com.n11.userservice.dto.client.RestaurantDto;
import com.n11.userservice.dto.client.RestaurantRecommendationDto;
import com.n11.userservice.general.GenericRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Created By Mustafa Aykurt
 * Date:16.03.2024
 * Time:23:47
 */
@FeignClient(name = "restaurant-service", url = "http://localhost:8080/api/v1/restaurants")
public interface RestaurantServiceClient {

    @GetMapping("/near")
    ResponseEntity<GenericRestResponse<List<RestaurantRecommendationDto>>> findRestaurantsNearUser(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam Integer distance);
}
