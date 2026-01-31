package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.Service.BookingReviewService;
import com.example.OnlineTicket.model.BookingReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class BookingReviewController {

    @Autowired
    private BookingReviewService bookingReviewService;

    @PostMapping("/bus/{busId}/passenger/{passengerId}")
    public ResponseEntity<BookingReview> addReview(
            @PathVariable Long busId,
            @PathVariable Long passengerId,
            @RequestBody BookingReview review) {

        BookingReview savedReview =
                bookingReviewService.addReview(busId, passengerId, review);

        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<BookingReview>> getReviewsByBus(@PathVariable Long busId) {
        return ResponseEntity.ok(
                bookingReviewService.getReviewsByBus(busId)
        );
    }

    @GetMapping("/passenger/{passengerId}")
    public ResponseEntity<List<BookingReview>> getReviewsByPassenger(
            @PathVariable Long passengerId) {

        return ResponseEntity.ok(
                bookingReviewService.getReviewsByPassenger(passengerId)
        );
    }
}