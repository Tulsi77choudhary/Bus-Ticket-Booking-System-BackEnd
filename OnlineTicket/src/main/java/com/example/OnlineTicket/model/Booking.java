package com.example.OnlineTicket.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


//    private Passenser passenser;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ElementCollection
    private List<String> seatNumbers;

    private int seatsBooked;

    private LocalDateTime bookingDate;

    private String status;


}

