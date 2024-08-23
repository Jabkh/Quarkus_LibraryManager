package com.example.service;

import com.example.client.BookServiceClient;
import com.example.client.UserServiceClient;
import com.example.domain.Review;
import com.example.dto.UserDTO;
import com.example.repository.ReviewRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;
import java.util.List;

public class ReviewService {

    @Inject
    ReviewRepository reviewRepository;

    @Inject
    @RestClient
    UserServiceClient userServiceClient;

    @Inject
    @RestClient
    BookServiceClient bookServiceClient;


    public List<Review> getReviews() {
        return reviewRepository.listAll();
    }

    @Transactional
    public Review createReview(Long userId, Long bookId, String reviewText, int rating) {
        // Check if user exists
        UserDTO user = userServiceClient.getAuthorById(userId);
        if (user == null) {
            throw new NotFoundException("User with id " + userId + " not found");
        }

        // Create and save review record
        Review review = new Review();
        review.setUserId(userId);
        review.setBookId(bookId);
        review.setReviewText(reviewText);
        review.setRating(rating);
        review.setReviewDate(LocalDate.now());
        reviewRepository.persist(review);

        return review;
    }

    public List<Review> findReviewsByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public Review findReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId);
        if (review == null) {
            throw new NotFoundException("Review with id " + reviewId + " not found");
        }
        return review;
    }

    @Transactional
    public Review updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId);
        if (review == null) {
            throw new NotFoundException("Review with id " + reviewId + " not found");
        }

        review.setUserId(updatedReview.getUserId());
        review.setBookId(updatedReview.getBookId());
        review.setReviewText(updatedReview.getReviewText());
        review.setRating(updatedReview.getRating());
        review.setReviewDate(updatedReview.getReviewDate());
        reviewRepository.persist(review);

        return review;
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId);
        if (review == null) {
            throw new NotFoundException("Review with id " + reviewId + " not found");
        }
        reviewRepository.delete(review);
    }
}