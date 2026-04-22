package com.trung.review.service.impl;

import com.trung.review.model.Review;
import com.trung.review.payload.dto.ReviewRequest;
import com.trung.review.payload.dto.SalonDTO;
import com.trung.review.payload.dto.UserDTO;
import com.trung.review.repository.ReviewRepository;
import com.trung.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(ReviewRequest request, UserDTO userDTO, SalonDTO salonDTO) {
        Review review = new Review();
        review.setReviewText(request.getReviewText());
        review.setRating(request.getRating());
        review.setSalonId(salonDTO.getId());
        review.setUserId(userDTO.getId());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsBySalonId(Long salonId) {
        return reviewRepository.findBySalonId(salonId);
    }

    private Review getReviewById(Long reviewId) throws Exception {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new Exception("Review not found with id: " + reviewId));
    }

    @Override
    public Review updateReview(ReviewRequest request, Long reviewId, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if (!review.getUserId().equals(userId)) {
            throw new Exception("You don't have permission to update this review");
        }
        review.setReviewText(request.getReviewText());
        review.setRating(request.getRating());
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if (!review.getUserId().equals(userId)) {
            throw new Exception("You don't have permission to delete this review");
        }
        reviewRepository.delete(review);
    }
}
