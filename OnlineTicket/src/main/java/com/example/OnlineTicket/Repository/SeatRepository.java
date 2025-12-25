package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.model.Bus;
import com.example.OnlineTicket.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat> findByBus_BusNumber(String busNumber);
    Optional<Seat> findBySeatNumber(String seatNumber);
    void deleteBySeatNumber(String seatNumber);
    List<Seat> findByBus(Bus bus);
    Optional<Seat> findBySeatNumberAndBus(String seatNumber, Bus bus);

   List<Seat> findByBusAndSeatNumberIn(Bus bus, List<String> seatNumbers);
}
