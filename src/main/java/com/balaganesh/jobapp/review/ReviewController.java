package com.balaganesh.jobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity< List<Review>> getAllReviews(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@RequestBody Review review, @PathVariable Long companyId) {

        Boolean isReviewSaved = reviewService.createReview(companyId, review);
        if (!isReviewSaved) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( "Review Created SuccessFully" ,HttpStatus.CREATED);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review == null) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);

    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review updatedReview) {
        Boolean isUpdated = reviewService.updateReviewById(companyId, reviewId, updatedReview);
        if (!isUpdated) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( "Review Updated SuccessFully" ,HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Boolean isDeleted = reviewService.deleteReviewById(companyId, reviewId);
        if (!isDeleted) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( "Review Deleted SuccessFully" ,HttpStatus.OK);
    }


}
