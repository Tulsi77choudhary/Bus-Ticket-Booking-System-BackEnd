package com.example.OnlineTicket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @ElementCollection
    private List<String> seatNumbers = new ArrayList<>();

    private double totalPrice;

    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
