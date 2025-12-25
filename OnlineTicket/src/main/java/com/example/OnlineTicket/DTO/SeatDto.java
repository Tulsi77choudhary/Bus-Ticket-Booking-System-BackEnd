package com.example.OnlineTicket.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeatDto {

    private String seatNumber;
    private String seatType;
    private double price;
    private String busNumber;



    public SeatDto(String seatNumber,String seatType,double price,String busNumber) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price= price;
        this.busNumber = busNumber;


    }

}
