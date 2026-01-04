package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.BookingRequest;
import com.example.OnlineTicket.DTO.BookingResponse;
import com.example.OnlineTicket.Repository.*;
import com.example.OnlineTicket.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private TicketService ticketService;

    private BookingResponse mapToBookingResponse(Booking bookingResponse) {

        BookingResponse response = new BookingResponse();
        response.setId(bookingResponse.getId());
        response.setUserId(bookingResponse.getId());
        response.setBusId(bookingResponse.getBus().getId());
        response.setSeatNumbers(bookingResponse.getSeatNumbers());
        response.setTotalAmount(bookingResponse.getTotalAmount());
        response.setBookingDate(bookingResponse.getBookingDate());
        response.setStatus(bookingResponse.getStatus());

        return response;
    }

    @Override
    @Transactional
    public BookingResponse bookTicket(BookingRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found"));

        Bus bus = busRepository.findById(request.getBusId())
                .orElseThrow(() ->
                        new RuntimeException("Bus not found"));
        List<Seat> seats = seatRepository.findAllByBusAndSeatNumberIn(bus,request.getSeatNumbers());

        if (seats.size() != request.getSeatNumbers().size()) {
            throw new RuntimeException("Invalid seat selection");
        }

        if (seats.stream().anyMatch(seat -> !seat.isAvailable())) {
            throw new RuntimeException("Seat already booked");
        }

        seats.forEach(seat -> seat.setAvailable(false));
        seatRepository.saveAll(seats);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBus(bus);
        booking.setSeatNumbers(request.getSeatNumbers());
        booking.setTotalAmount(seats.stream()
                .mapToDouble(Seat::getPrice)
                .sum()
        );
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus(BookingStatus.CONFIRMED);

        bookingRepository.save(booking);


        Passenger passenger = new Passenger();
        passenger.setName(request.getPassengerName());
        passenger.setAge(request.getPassengerAge());
        passenger.setGender(request.getPassengerGender());
        passenger.setEmail(request.getPassengerEmail());
        passenger.setUser(user);
        passenger.setBus(bus);
        passenger.setBooking(booking);

        passengerRepository.save(passenger);

        return mapToBookingResponse(booking);
    }

    @Override
    public List<BookingResponse> getUserBookings(Long userId) {
       return null;
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        return null;
    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
        bookingRepository.delete(booking);
    }
    public List<BookingResponse> getBookingsByBus(Long busId) {
        return null;
    }

    @Override
    public Booking bookTicket(User user, Passenger passengerDetail) {
        return null;
    }


}
