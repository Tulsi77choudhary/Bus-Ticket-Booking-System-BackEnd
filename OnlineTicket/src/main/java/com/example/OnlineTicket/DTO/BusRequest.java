package com.example.OnlineTicket.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BusRequest {
    private String busNumber;
    private String source;
    private String destination;
    private LocalDate TravelDate;

}
