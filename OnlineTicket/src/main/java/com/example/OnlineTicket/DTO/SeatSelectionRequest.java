package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.Bus;
import lombok.Data;

import java.util.List;

@Data
public class SeatSelectionRequest {
    private String busNumber;
    private List<String> seatNumbers;

}
