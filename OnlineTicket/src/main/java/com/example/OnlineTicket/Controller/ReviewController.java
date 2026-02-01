package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.DTO.ReviewRequest;
import com.example.OnlineTicket.Service.ReviewService;
import com.example.OnlineTicket.Service.UserService;
import com.example.OnlineTicket.model.Review;
import com.example.OnlineTicket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService bookingReviewService;
    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<Review> createReview(
            @RequestBody ReviewRequest request,
            @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserProfileByJwt(jwt);

        Review review = bookingReviewService.createReview(request,user);

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<Review>> getReviewsByBus(@PathVariable Long busId) {
        return ResponseEntity.ok(
                bookingReviewService.getReviewsByBus(busId)
        );
    }

}