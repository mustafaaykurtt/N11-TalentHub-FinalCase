package com.n11.restaurantservice.controller;

import com.n11.restaurantservice.dto.request.RestaurantSaveRequest;
import com.n11.restaurantservice.dto.request.RestaurantUpdateRequest;
import com.n11.restaurantservice.dto.response.RestaurantDto;
import com.n11.restaurantservice.general.GenericRestResponse;
import com.n11.restaurantservice.model.Restaurant;
import com.n11.restaurantservice.repository.RestaurantRepository;
import com.n11.restaurantservice.service.RestaurantService;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created By Mustafa Aykurt
 * Date:15.03.2024
 * Time:00:29
 */

@RestController
@RequestMapping("api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final SolrTemplate solrTemplate;

    public RestaurantController(RestaurantService restaurantService, RestaurantRepository restaurantRepository, SolrTemplate solrTemplate) {
        this.restaurantService = restaurantService;
        this.solrTemplate = solrTemplate;
    }

    @PostMapping("/restaurants/near")
    public List<Restaurant> findRestaurantsNearUser(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Integer distance) {
        restaurantService.findRestaurantsNearUser(latitude, longitude, distance);

        return null;
    }

    @PostMapping
    public ResponseEntity<GenericRestResponse> saveRestaurant(@RequestBody RestaurantSaveRequest request) {
        restaurantService.saveRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(GenericRestResponse.of("successful"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericRestResponse<RestaurantDto>> updateRestaurant(@Valid @PathVariable String id,
                                                                               @RequestBody RestaurantUpdateRequest request) {
        var restaurantDto = restaurantService.updateRestaurant(request);
        return ResponseEntity.status(200).body(GenericRestResponse.of(restaurantDto, "success"));
    }

    @GetMapping
    public ResponseEntity<GenericRestResponse<List<RestaurantDto>>> getAllRestaurants() {
        var restaurantDto = restaurantService.getAllRestaurants();
        return ResponseEntity.status(200).body(GenericRestResponse.of(restaurantDto, "success"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericRestResponse> deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.status(200).body(GenericRestResponse.of("Restaurant is deleted"));
    }

}
