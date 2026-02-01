package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.ReviewRequest;
import com.example.OnlineTicket.Repository.ReviewRepository;
import com.example.OnlineTicket.Repository.PassengerRepository;
import com.example.OnlineTicket.model.Review;
import com.example.OnlineTicket.model.Bus;
import com.example.OnlineTicket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository ReviewRepository;

    @Autowired
    private BusService busService;


    @Override
    public Review createReview(ReviewRequest request, User user) {
        Bus bus = busService.findBusbyId(request.getBusId());

        Review review = new Review();
        review.setUser(user);
        review.setBus(bus);
        review.setReview(request.getReview());
        review.setCreateAt(LocalTime.now());

        return ReviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByBus(Long busId) {
        return ReviewRepository.findByBusId(busId);
    }

}
