package com.n11.userservice.repository;

import com.n11.userservice.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created By Mustafa Aykurt
 * Date:28.02.2024
 * Time:23:09
 */
@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {

}
