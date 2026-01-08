package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.model.Booking;
import com.example.OnlineTicket.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByBusId(Long busId);
    List<Booking> findByBus(Bus bus);
}
