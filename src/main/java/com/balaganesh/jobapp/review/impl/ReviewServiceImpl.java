package com.balaganesh.jobapp.review.impl;

import com.balaganesh.jobapp.company.Company;
import com.balaganesh.jobapp.company.CompanyService;
import com.balaganesh.jobapp.review.Review;
import com.balaganesh.jobapp.review.ReviewRepository;
import com.balaganesh.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }


    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId( companyId);
    }

    @Override
    public Boolean createReview(Long companyId , Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return false;
        }
        review.setCompany(company);
        reviewRepository.save(review);
        return true;


    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        for (Review review : reviews) {
            if (review.getId().equals(reviewId)) {
                return review;
            }
        }
        return null;
    }

    @Override
    public Boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview) {

        if (companyService.getCompanyById(companyId) == null) {
            return false;
        }

        updatedReview.setId(reviewId);
        updatedReview.setCompany(companyService.getCompanyById(companyId));
        reviewRepository.save(updatedReview);
        return true;
    }

    @Override
    public Boolean deleteReviewById(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) == null || reviewRepository.findById(reviewId).isEmpty()) {
            return false;
        }

        Review review = reviewRepository.findById(reviewId).get();
        Company company = companyService.getCompanyById(companyId);
        company.getReviews().remove(review);
        review.setCompany(null);
        companyService.UpdateCompanyById(companyId, company);

        reviewRepository.deleteById(reviewId);
        return true;
    }
}
