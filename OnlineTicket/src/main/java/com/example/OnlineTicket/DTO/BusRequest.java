package com.example.OnlineTicket.DTO;

import lombok.Data;

@Data
public class BusRequest {
    private String busNumber;
    private String source;
    private String destination;

}
