package com.example.OnlineTicket.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BusDTO {
    private Long id;
    private String busNumber;
    private String source;
    private String destination;
    private LocalDate date;
    @JsonFormat(pattern = "hh:mm a")
    private LocalTime time;
    private int totalSeats;
    private List<SeatDto> seats;
}
