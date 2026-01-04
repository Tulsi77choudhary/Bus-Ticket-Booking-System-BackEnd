package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.DTO.BookingDto;
import com.example.OnlineTicket.DTO.BookingRequest;
import com.example.OnlineTicket.DTO.BookingResponse;
import com.example.OnlineTicket.model.User;
import com.example.OnlineTicket.Repository.BookingRepository;
import com.example.OnlineTicket.Repository.SeatRepository;
import com.example.OnlineTicket.Service.BookingService;
import com.example.OnlineTicket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<BookingResponse> bookTicket(@RequestBody BookingRequest request) {

        User user = userService.findById(request.getUserId());

        BookingResponse response = bookingService.bookTicket(request);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/api/bookings")
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingDto::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/user/{userId}")
    public List<BookingResponse> getUserBookings(@PathVariable Long userId) {
        return bookingService.getUserBookings(userId);
    }
    @GetMapping("/bus/{busId}")
    public List<BookingResponse> getBookingsByBus(@PathVariable Long busId) {
        return bookingService.getBookingsByBus(busId);
    }

    @GetMapping("booking/{id}")
    public BookingResponse getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "Booking deleted successfully.";
    }

}
