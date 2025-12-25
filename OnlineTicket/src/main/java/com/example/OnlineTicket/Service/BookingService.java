package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.BookingResponse;

import java.util.List;

public interface BookingService {

    BookingResponse bookTicket(Long userId, Long busId, List<String> seats);

    List<BookingResponse> getUserBookings(Long userId);

    BookingResponse getBookingById(Long id);

    void deleteBooking(Long id);

    List<BookingResponse> getBookingsByBus(Long busId);
}
