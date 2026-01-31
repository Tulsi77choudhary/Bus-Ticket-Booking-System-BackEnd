package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.DTO.BookingDto;
import com.example.OnlineTicket.DTO.BookingRequest;
import com.example.OnlineTicket.DTO.BookingRequestDto;
import com.example.OnlineTicket.DTO.BookingResponse;
import com.example.OnlineTicket.Excaption.BookingException;
import com.example.OnlineTicket.Excaption.BusException;
import com.example.OnlineTicket.Excaption.UserException;
import com.example.OnlineTicket.model.User;
import com.example.OnlineTicket.Repository.BookingRepository;
import com.example.OnlineTicket.Repository.SeatRepository;
import com.example.OnlineTicket.Service.BookingService;
import com.example.OnlineTicket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<BookingResponse> bookTicket(
            @RequestBody BookingRequest request,
            @RequestHeader("Authorization") String jwt) throws BookingException, UserException, BusException {

        User user = userService.findUserProfileByJwt(jwt);
        BookingResponse response = bookingService.bookTicket(request,user);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/bookings")
    public List<BookingDto> getAllBookings() throws BookingException {
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

    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "Booking deleted successfully.";
    }

}
