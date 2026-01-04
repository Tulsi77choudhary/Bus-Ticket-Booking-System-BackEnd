package com.example.OnlineTicket.DTO;

import lombok.Data;

import java.util.List;


@Data
public class BookingRequest {
    private Long userId;
    private Long busId;
    private List<String> seatNumbers;

    private String passengerName;
    private int passengerAge;
    private String passengerGender;
    private String passengerEmail;
}
