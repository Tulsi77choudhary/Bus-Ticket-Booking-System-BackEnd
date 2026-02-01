package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.ReviewRequest;
import com.example.OnlineTicket.model.Review;
import com.example.OnlineTicket.model.User;

import java.util.List;

public interface ReviewService {

    Review createReview(ReviewRequest request, User user);

    List<Review> getReviewsByBus(Long busId);

}
