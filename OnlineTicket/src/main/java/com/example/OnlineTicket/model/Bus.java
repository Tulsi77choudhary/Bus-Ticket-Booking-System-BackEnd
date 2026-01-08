package com.example.OnlineTicket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busName;
    @Column(name = "bus_number", unique = true, nullable = false)
    private String busNumber;

    private String source;
    private String destination;

    @Enumerated(EnumType.STRING)
    private BusType busType;
    private LocalTime time;
    private LocalDate date;
    private int totalSeats;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusStatus status;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

}
