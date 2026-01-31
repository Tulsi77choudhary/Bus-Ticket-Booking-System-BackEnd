package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class BookingRequestDto {
    private Long bookingId;
    private String userName;
    private String userEmail;

    private String busNumber;
    private String busName;
    private String from;
    private String to;
    private LocalTime departureTime;

    private List<String> seats;

    private double totalAmount;
    private BookingStatus status;
    private LocalDateTime bookingDate;
}
