package com.example.OnlineTicket.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BookingResponse {
    private Long id;
    private String userId;
    private String busId;
    private List<String> seatNumbers;
    private LocalDateTime bookingDate;
    private int seatsBooked;
    private String status;

    public BookingResponse(Long id, String userId, String busId,
                           List<String> seatNumbers, LocalDateTime bookingDate,
                           int seatsBooked, String status) {
        this.id = id;
        this.userId = userId;
        this.busId = busId;
        this.seatNumbers = seatNumbers;
        this.bookingDate = bookingDate;
        this.seatsBooked = seatsBooked;
        this.status = status;
    }

}

