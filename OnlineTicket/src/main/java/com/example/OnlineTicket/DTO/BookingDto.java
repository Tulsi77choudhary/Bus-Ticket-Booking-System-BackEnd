package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.Booking;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDto {
    private Long id;
    private String userName;
    private String busNumber;
    private List<String> seatNumbers;
    private LocalDateTime bookingDate;
    public BookingDto(Booking booking) {
        this.id = booking.getId();
        this.userName = booking.getUser().getName();
        this.busNumber = booking.getBus().getBusNumber();
        this.bookingDate = booking.getBookingDate();

    }
}
