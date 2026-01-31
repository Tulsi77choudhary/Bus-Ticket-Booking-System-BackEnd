package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.BusStatus;
import com.example.OnlineTicket.model.BusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private int totalSeats;
    private BusStatus status;
    private List<SeatDto> seats;


    public BusResponseDTO(Long id, String busNumber, String source, String destination, LocalDate date, LocalTime time, LocalTime time1, int totalSeats, BusStatus status) {
    }
}

