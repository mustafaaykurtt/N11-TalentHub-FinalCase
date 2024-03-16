package com.n11.restaurantservice.util.mapper;

import com.n11.restaurantservice.dto.request.RestaurantSaveRequest;
import com.n11.restaurantservice.dto.response.RestaurantDto;
import com.n11.restaurantservice.model.Restaurant;
import org.mapstruct.*;

/**
 * Created By Mustafa Aykurt
 * Date:15.03.2024
 * Time:02:45
 */

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {
    Restaurant convertToRestaurant(RestaurantSaveRequest request);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
//    @Mapping(target = "id", ignore = true)
//    void updateUserReviewFromDTO(UserReviewUpdateRequest request, @MappingTarget UserReview review);


    RestaurantDto convertToRestaurantDto(Restaurant restaurant);
}
