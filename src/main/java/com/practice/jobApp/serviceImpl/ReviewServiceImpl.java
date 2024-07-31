package com.practice.jobApp.serviceImpl;

import com.practice.jobApp.entity.Company;
import com.practice.jobApp.entity.Job;
import com.practice.jobApp.entity.Review;
import com.practice.jobApp.repository.ReviewRepository;
import com.practice.jobApp.service.CompanyService;
import com.practice.jobApp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review createReview(Long companyId, Review review) {
        Optional<Company> company = companyService.getCompanyById(companyId);
        if (company.isPresent()) {
            review.setCompany(company.get());
            reviewRepository.save(review);
            return review;
        } else {
            return null;
        }
    }

    @Override
    public Review updateReview(Long companyId, Review review) {
        Optional<Review> reviewOptional = reviewRepository.findById(review.getId());
        if (reviewOptional != null) {
            Review reviewObj = reviewOptional.get();
            reviewObj.setTitle(review.getTitle());
            reviewObj.setDescription(review.getDescription());
            reviewObj.setRating(review.getRating());
            reviewRepository.save(reviewObj);
            return reviewObj;
        } else
            return null;
    }

    @Override
    public Boolean deleteJob(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
