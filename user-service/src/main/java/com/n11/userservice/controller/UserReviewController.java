package com.n11.userservice.controller;

import com.n11.userservice.dto.request.UserReviewSaveRequest;
import com.n11.userservice.dto.response.UserReviewDto;
import com.n11.userservice.dto.request.UserReviewUpdateRequest;
import com.n11.userservice.general.GenericRestResponse;
import com.n11.userservice.service.UserReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created By Mustafa Aykurt
 * Date:11.03.2024
 * Time:22:58
 */

@RestController
@RequestMapping("/api/v1/user-reviews")
public class UserReviewController {
    private final UserReviewService userReviewService;

    public UserReviewController(UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }

    @PostMapping
    public ResponseEntity<GenericRestResponse> saveUserReview(@Valid @RequestBody UserReviewSaveRequest request) {
        userReviewService.saveReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(GenericRestResponse.of("n11.create.success"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericRestResponse> deleteUserReview(@PathVariable Long id) {
        userReviewService.deleteUser(id);
        return ResponseEntity.status(200).body(GenericRestResponse.of("Review is deleted"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericRestResponse<UserReviewDto>> updateReviewDetails(@PathVariable Long id,
                                                                   @RequestBody UserReviewUpdateRequest request) {
        var userReviewDto = userReviewService.updateReviewDetails(request);
        return ResponseEntity.status(200).body(GenericRestResponse.of(userReviewDto, "n11.update.user.success"));
    }


}
