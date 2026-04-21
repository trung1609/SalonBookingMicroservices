package com.trung.review.controller;

import com.trung.review.model.Review;
import com.trung.review.payload.dto.ApiResponse;
import com.trung.review.payload.dto.ReviewRequest;
import com.trung.review.payload.dto.SalonDTO;
import com.trung.review.payload.dto.UserDTO;
import com.trung.review.service.ReviewService;
import com.trung.review.service.client.SalonFeignClient;
import com.trung.review.service.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final UserFeignClient userFeignClient;
    private final SalonFeignClient salonFeignClient;

    @PostMapping("/salon/{salonId}")
    public ResponseEntity<Review> createReview(@PathVariable Long salonId,
                                               @RequestBody ReviewRequest request,
                                               @RequestHeader("Authorization") String jwt) throws Exception {

        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();
        SalonDTO salon = salonFeignClient.getSalonById(salonId).getBody();
        Review review = reviewService.createReview(request, user, salon);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<Review>> getReviewsBySalonId(@PathVariable Long salonId,
                                               @RequestHeader("Authorization") String jwt) throws Exception {

        SalonDTO salon = salonFeignClient.getSalonById(salonId).getBody();
        List<Review> review = reviewService.getReviewsBySalonId(salon.getId());
        return ResponseEntity.ok(review);
    }

    @PutMapping("/salon/{salonId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId,
                                                            @RequestBody ReviewRequest request,
                                                            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();
        Review review = reviewService.updateReview(request, reviewId, user.getId());
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long reviewId,
                                                    @RequestHeader("Authorization") String jwt) throws Exception {
        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();
        reviewService.deleteReview(reviewId, user.getId());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Review deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
