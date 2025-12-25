package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.Seat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BusResponseDTO {
    private String busNumber;
    private String source;
    private String destination;
    private LocalDate date;
    private LocalTime time;
    private int  totalSeats;
    private List<SeatDto> seats;
    public BusResponseDTO(String busNumber, String source, String destination,
                          LocalDate date, LocalTime time, int totalSeats,List<SeatDto> seats) {
        this.busNumber = busNumber;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.totalSeats =totalSeats;
        this.seats = seats;
    }

}
