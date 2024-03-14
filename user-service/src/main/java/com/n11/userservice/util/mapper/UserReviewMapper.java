package com.n11.userservice.util.mapper;

import com.n11.userservice.dto.request.UserReviewSaveRequest;
import com.n11.userservice.dto.request.UserReviewUpdateRequest;
import com.n11.userservice.dto.response.UserReviewDto;
import com.n11.userservice.model.UserReview;
import org.mapstruct.*;

/**
 * Created By Mustafa Aykurt
 * Date:12.03.2024
 * Time:02:38
 */

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserReviewMapper {
    UserReview convertToUserReview(UserReviewSaveRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "id", ignore = true)
    void updateUserReviewFromDTO(UserReviewUpdateRequest request, @MappingTarget UserReview review);


    UserReviewDto convertToUserReviewDTO(UserReview userReview);
}
