package com.example.OnlineTicket.DTO;

import lombok.Data;

import java.util.List;

@Data
public class SeatSelectionRequest {
    private String busNumber;
    private List<String> seatNumbers;


//    public SeatSelectionRequest(String busNumber,List<String> seatNumbers){
//        this.busNumber = busNumber;
//        this.seatNumbers = seatNumbers;
//    }
}
