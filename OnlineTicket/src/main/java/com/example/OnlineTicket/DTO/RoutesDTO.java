package com.example.OnlineTicket.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutesDTO {
    private Long id;
    private String busNumber;
    private String source;
    private String destination;
    private LocalDate date;
    private LocalTime time;
    private double cost;


}
