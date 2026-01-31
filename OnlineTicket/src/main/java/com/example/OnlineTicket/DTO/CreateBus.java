package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.BusStatus;
import com.example.OnlineTicket.model.BusType;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CreateBus {


    public BusStatus getStatus;
    private String busName;
    private String busNumber;
    private String source;
    private String destination;
    private LocalDate date;
    private LocalTime time;
    private BusType busType;
    private int totalSeats;
    private BusStatus status;

}
