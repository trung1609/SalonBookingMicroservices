package com.trung.review.mapper;

import com.trung.review.model.Review;
import com.trung.review.payload.dto.ReviewDTO;
import com.trung.review.payload.dto.SalonDTO;
import com.trung.review.payload.dto.UserDTO;

public class ReviewMapper {
    public static ReviewDTO toDTO(Review review, UserDTO userDTO, SalonDTO salonDTO) {
        return ReviewDTO.builder()
                .id(review.getId())
                .user(userDTO)
                .salon(salonDTO)
                .reviewText(review.getReviewText())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
