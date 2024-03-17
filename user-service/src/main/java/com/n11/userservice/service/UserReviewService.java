package com.n11.userservice.service;

import com.n11.userservice.client.RestaurantServiceClient;
import com.n11.userservice.dto.request.UserReviewSaveRequest;
import com.n11.userservice.dto.request.UserReviewUpdateRequest;
import com.n11.userservice.dto.response.UserReviewDto;
import com.n11.userservice.exception.NotFoundException;
import com.n11.userservice.general.base.BaseAdditionalFields;
import com.n11.userservice.model.UserReview;
import com.n11.userservice.repository.UserReviewRepository;
import com.n11.userservice.util.mapper.UserReviewMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


/**
 * Created By Mustafa Aykurt
 * Date:28.02.2024
 * Time:23:11
 */
@Service
public class UserReviewService {

    private final UserReviewRepository userReviewRepository;
    private final UserReviewMapper reviewMapper;

    public UserReviewService(UserReviewRepository userReviewRepository, UserReviewMapper reviewMapper, RestaurantServiceClient restaurantServiceClient) {
        this.userReviewRepository = userReviewRepository;
        this.reviewMapper = reviewMapper;
    }
    public void saveReview(UserReviewSaveRequest request) {
        var userReview = reviewMapper.convertToUserReview(request);
        var baseAdditionalFields = new BaseAdditionalFields();
        baseAdditionalFields.setUpdateDate(LocalDateTime.now());
        baseAdditionalFields.setCreateDate(LocalDateTime.now());
        userReview.setBaseAdditionalFields(baseAdditionalFields);
        userReviewRepository.save(userReview);
    }

    private UserReview getUserReviewById(long id) {
        return userReviewRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public UserReviewDto updateReviewDetails(UserReviewUpdateRequest request) {
        var inDB = getUserReviewById(request.id());
        reviewMapper.updateUserReviewFromDTO(request,inDB);
        inDB.getBaseAdditionalFields().setUpdateDate(LocalDateTime.now());
        return reviewMapper.convertToUserReviewDTO(userReviewRepository.save(inDB));
    }

    public void deleteUser(Long id) {
        var inDB = getUserReviewById(id);
        userReviewRepository.delete(inDB);
    }



}
