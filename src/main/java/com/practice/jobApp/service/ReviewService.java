package com.practice.jobApp.service;

import com.practice.jobApp.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    Optional<Review> getReviewById(Long id);

    Review createReview(Long companyId, Review review);

    Review updateReview(Long companyId, Review review);

    Boolean deleteJob(Long id);
}
