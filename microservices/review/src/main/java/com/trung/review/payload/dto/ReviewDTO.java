package com.trung.review.payload.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private Long id;
    private UserDTO user;
    private SalonDTO salon;
    private String reviewText;
    private double rating;
    private LocalDateTime createdAt;
}
