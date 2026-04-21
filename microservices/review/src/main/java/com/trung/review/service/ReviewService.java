package com.trung.review.service;

import com.trung.review.model.Review;
import com.trung.review.payload.dto.ReviewRequest;
import com.trung.review.payload.dto.SalonDTO;
import com.trung.review.payload.dto.UserDTO;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewRequest request, UserDTO userDTO, SalonDTO salonDTO);

    List<Review> getReviewsBySalonId(Long salonId);

    Review updateReview(ReviewRequest request, Long reviewId, Long userId) throws Exception;

    void deleteReview(Long reviewId, Long userId) throws Exception;
}
