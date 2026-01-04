package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data

public class BookingResponse {
    private Long id;
    private Long userId;
    private Long busId;
    private List<String> seatNumbers;
    private double totalAmount;
    private LocalDateTime bookingDate;
    private BookingStatus status;
    public BookingResponse() {
    }
}

