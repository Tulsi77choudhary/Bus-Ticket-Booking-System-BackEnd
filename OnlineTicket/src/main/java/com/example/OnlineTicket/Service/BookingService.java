package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.BookingRequest;
import com.example.OnlineTicket.DTO.BookingRequestDto;
import com.example.OnlineTicket.DTO.BookingResponse;
import com.example.OnlineTicket.model.User;

import java.util.List;

public interface BookingService {

    BookingResponse bookTicket(BookingRequest request, User user);

    List<BookingResponse> getUserBookings(Long userId);

    BookingResponse getBookingById(Long id);

    void deleteBooking(Long id);

    List<BookingResponse> getBookingsByBus(Long busId);

    BookingRequestDto getByBookingId(Long bookingId);
}
