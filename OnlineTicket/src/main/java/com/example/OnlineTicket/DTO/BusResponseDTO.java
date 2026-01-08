package com.example.OnlineTicket.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BusResponseDTO {
    private Long id;
    private String busNumber;
    private String source;
    private String destination;
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime arrivalTime;

    private int  totalSeats;
    private List<SeatDto> seats;


    public BusResponseDTO(Long id, String busNumber, String source, String destination, int totalSeats) {

    }
}
