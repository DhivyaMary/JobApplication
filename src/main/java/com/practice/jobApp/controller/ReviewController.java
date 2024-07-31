package com.practice.jobApp.controller;

import com.practice.jobApp.entity.Company;
import com.practice.jobApp.entity.Review;
import com.practice.jobApp.service.CompanyService;
import com.practice.jobApp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{reviews}")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if (!reviews.isEmpty()) return new ResponseEntity<>(reviews, HttpStatus.OK);
        else return new ResponseEntity("Reviews Not Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long id) {
        Optional<Company> company = companyService.getCompanyById(companyId);
        if (company.isPresent()) {
            Optional<Review> review = reviewService.getReviewById(id);
            if (review.isPresent()) {
                return new ResponseEntity(review, HttpStatus.OK);
            } else return new ResponseEntity("No Reviews Found for Id " + id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity("No Company Found for Id " + companyId, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        Review reviewObj = reviewService.createReview(companyId, review);
        if (reviewObj != null) {
            return new ResponseEntity(reviewObj, HttpStatus.OK);
        } else return new ResponseEntity("Review Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews")
    public ResponseEntity<Review> updateReview(@PathVariable Long companyId, @RequestBody Review review) {
        Review reviewObj = reviewService.updateReview(companyId, review);
        if (reviewObj != null) {
            return new ResponseEntity(reviewObj, HttpStatus.OK);
        } else return new ResponseEntity("Review Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        Boolean isDeleted = reviewService.deleteJob(id);
        if (isDeleted)
            return new ResponseEntity("Review Deleted Success", HttpStatus.OK);
        else
            return new ResponseEntity("No Review Found to Delete", HttpStatus.NOT_FOUND);
    }


}
