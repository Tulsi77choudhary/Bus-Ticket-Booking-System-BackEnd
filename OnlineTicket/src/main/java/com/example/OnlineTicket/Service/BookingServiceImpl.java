package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.BookingResponse;
import com.example.OnlineTicket.Repository.BookingRepository;
import com.example.OnlineTicket.Repository.BusRepository;
import com.example.OnlineTicket.Repository.SeatRepository;
import com.example.OnlineTicket.Repository.UserRepository;
import com.example.OnlineTicket.model.Booking;
import com.example.OnlineTicket.model.Bus;
import com.example.OnlineTicket.model.Seat;
import com.example.OnlineTicket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BusRepository busRepository;

    @Override
    public BookingResponse bookTicket(Long userId, Long busId, List<String> seatNumbers) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        for (String seatNumber : seatNumbers) {
            Seat seat = seatRepository.findBySeatNumberAndBus(seatNumber, bus)
                    .orElseThrow(() -> new RuntimeException("Seat not found: " + seatNumber));

            seatRepository.save(seat);
        }
        Booking booking = Booking.builder()
                .user(user)
                .bus(bus)
                .seatNumbers(seatNumbers)
                .seatsBooked(seatNumbers.size())
                .bookingDate(LocalDateTime.now())
                .status("CONFIRMED")
                .build();

        Booking saved = bookingRepository.save(booking);
        return mapToResponse(saved);
    }

    @Override
    public List<BookingResponse> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
        return mapToResponse(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
        bookingRepository.delete(booking);
    }

    private BookingResponse mapToResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .userId(booking.getUser().getName())
                .busId(booking.getBus().getBusNumber())
                .seatNumbers(booking.getSeatNumbers())
                .bookingDate(booking.getBookingDate())
                .seatsBooked(booking.getSeatsBooked())
                .status(booking.getStatus())
                .build();
    }


    public List<BookingResponse> getBookingsByBus(Long busId) {
        List<Booking> bookings = bookingRepository.findByBusId(busId);

        return bookings.stream()
                .map(booking -> BookingResponse.builder()
                        .id(booking.getId())
                        .userId(booking.getUser().getName())
                        .busId(booking.getBus().getBusNumber())
                        .seatNumbers(booking.getSeatNumbers())
                        .bookingDate(booking.getBookingDate())
                        .seatsBooked(booking.getSeatsBooked())
                        .status(booking.getStatus())
                        .build()
                ).collect(Collectors.toList());
    }


}
