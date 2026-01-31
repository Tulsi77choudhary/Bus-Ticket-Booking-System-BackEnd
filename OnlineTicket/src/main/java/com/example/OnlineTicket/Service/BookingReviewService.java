package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.model.BookingReview;

import java.util.List;

public interface BookingReviewService {

    BookingReview addReview(Long busId, Long passengerId, BookingReview review);

    List<BookingReview> getReviewsByBus(Long busId);

    List<BookingReview> getReviewsByPassenger(Long passengerId);
}
