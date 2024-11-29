package com.balaganesh.jobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Boolean createReview( Long companyId,Review review);

    Review getReviewById(Long companyId, Long reviewId);
    Boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview);

    Boolean deleteReviewById(Long companyId, Long reviewId);
}
