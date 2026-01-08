package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.BusType;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CreateBus {

    private String busName;
    private String busNumber;
    private String source;
    private String destination;
    private LocalDate date;
    private LocalTime Time;
    private BusType busType;
    private int totalSeats;
    private List<SeatDto> seats;
}
