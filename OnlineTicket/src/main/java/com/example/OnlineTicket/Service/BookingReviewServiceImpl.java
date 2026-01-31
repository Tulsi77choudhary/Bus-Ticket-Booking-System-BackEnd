package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.Repository.BookingReviewRepository;
import com.example.OnlineTicket.Repository.BusRepository;
import com.example.OnlineTicket.Repository.PassengerRepository;
import com.example.OnlineTicket.model.BookingReview;
import com.example.OnlineTicket.model.Bus;
import com.example.OnlineTicket.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookingReviewServiceImpl implements BookingReviewService {

    @Autowired
    private BookingReviewRepository bookingReviewRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public BookingReview addReview(Long busId, Long passengerId, BookingReview review) {

        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        review.setBus(bus);
        review.setPassenger(passenger);

        return bookingReviewRepository.save(review);
    }

    @Override
    public List<BookingReview> getReviewsByBus(Long busId) {
        return bookingReviewRepository.findByBusId(busId);
    }

    @Override
    public List<BookingReview> getReviewsByPassenger(Long passengerId) {
        return bookingReviewRepository.findByPassengerId(passengerId);
    }
}
