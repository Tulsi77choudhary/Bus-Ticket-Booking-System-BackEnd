package com.example.OnlineTicket.DTO;

import lombok.Data;
import java.util.List;
@Data
public class SeatSelectionRequest {
    private String busNumber;
    private List<String> seatNumbers;

}
