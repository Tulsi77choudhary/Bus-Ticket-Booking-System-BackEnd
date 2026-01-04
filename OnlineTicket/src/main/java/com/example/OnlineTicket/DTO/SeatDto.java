package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatDto {

    private Long id;
    private String busNumber;
    private String seatNumber;
    private SeatType seatType;
    private boolean available;
    private double price;
    public SeatDto() {}


}
