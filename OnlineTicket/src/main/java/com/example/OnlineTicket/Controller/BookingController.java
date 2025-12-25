package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.DTO.BookingDto;
import com.example.OnlineTicket.DTO.BookingRequest;
import com.example.OnlineTicket.DTO.BookingResponse;
import com.example.OnlineTicket.DTO.SeatDto;
import com.example.OnlineTicket.Repository.BookingRepository;
import com.example.OnlineTicket.Repository.SeatRepository;
import com.example.OnlineTicket.Service.BookingService;
import com.example.OnlineTicket.Service.SeatService;
import com.example.OnlineTicket.model.Booking;
import com.example.OnlineTicket.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("/api/bookings")
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingDto::new)
                .collect(Collectors.toList());
    }


    @PostMapping
    public BookingResponse bookTicket(@RequestBody BookingRequest bookingRequest) {
        return bookingService.bookTicket(
                bookingRequest.getUserId(),
                bookingRequest.getBusId(),
                bookingRequest.getSeatNumbers()
        );
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
//    @PutMapping("/seats/{seatNumber}/cancel")
//    public ResponseEntity<SeatDto> cancelSeat(@PathVariable String seatNumber) {
//        List<Seat> optionalSeat = seatRepository.findBySeatNumber(seatNumber);
//        if (optionalSeat.isEmpty()) return ResponseEntity.notFound().build();
//
//        Seat seat = optionalSeat.get(0);
//        if (seat.isAvailable()) return ResponseEntity.badRequest().body(new SeatDto(seat));
//
//        seat.setAvailable(true);
//        seatRepository.save(seat);
//        return ResponseEntity.ok(new SeatDto(seat));
//    }


    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "Booking deleted successfully.";
    }

}
