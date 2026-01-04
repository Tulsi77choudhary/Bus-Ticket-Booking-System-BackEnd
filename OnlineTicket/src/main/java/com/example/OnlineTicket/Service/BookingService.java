package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.BookingRequest;
import com.example.OnlineTicket.DTO.BookingResponse;
import com.example.OnlineTicket.model.Booking;
import com.example.OnlineTicket.model.Passenger;
import com.example.OnlineTicket.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookingService {

    BookingResponse bookTicket(BookingRequest request);

    List<BookingResponse> getUserBookings(Long userId);

    BookingResponse getBookingById(Long id);

    void deleteBooking(Long id);

    List<BookingResponse> getBookingsByBus(Long busId);

    Booking bookTicket(User user, Passenger passengerDetail);
}
