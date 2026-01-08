package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.model.Bus;
import com.example.OnlineTicket.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    @Query("SELECT s FROM Seat s WHERE s.bus.busNumber = :busNumber")
    List<Seat> findByBusNumber(@Param("busNumber") String busNumber);

    Optional<Seat> findBySeatNumber(String seatNumber);
    List<Seat> findByBus(Bus bus);
    List<Seat> findAllByBusAndSeatNumberIn(Bus bus, List<String> seatNumbers);

    @Query("SELECT s FROM Seat s WHERE s.bus.busNumber = :busNumber AND s.seatNumber IN :seatNumbers")
    List<Seat> findByBusNumberAndSeatNumbers(@Param("busNumber") String busNumber,
                                             @Param("seatNumbers") List<String> seatNumbers);

    Optional<Seat> findBySeatNumberAndBus(String seatNumber, Bus bus);

}
