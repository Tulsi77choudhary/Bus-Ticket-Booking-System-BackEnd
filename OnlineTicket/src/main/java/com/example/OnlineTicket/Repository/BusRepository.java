package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByBusNumber(String busNumber);
    List<Bus> findBySourceAndDestination(String source, String destination);
    List<Bus> findBySource(String source);
    List<Bus> findByDestination(String destination);
    List<Bus> findBySourceAndDestinationAndBusNumber(String source, String destination, String busNumber);

    Optional<Bus> findByBusNumberIgnoreCase(String busNumber);
}

