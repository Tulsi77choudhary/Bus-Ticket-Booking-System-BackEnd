package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    Optional<Bus> findByBusNumber(String busNumber);
    boolean existsByBusNumber(String busNumber);

    @Query("""
        SELECT b FROM Bus b
        WHERE LOWER(b.source) = LOWER(:source)
        AND LOWER(b.destination) = LOWER(:destination)
    """)
    List<Bus> findBySourceAndDestination(String source, String destination);

    @Query("""
        SELECT b FROM Bus b
        WHERE (:busNumber IS NULL OR b.busNumber = :busNumber)
          AND (:source IS NULL OR LOWER(b.source) LIKE LOWER(CONCAT('%', :source, '%')))
          AND (:destination IS NULL OR LOWER(b.destination) LIKE LOWER(CONCAT('%', :destination, '%')))
    """)
    List<Bus> searchBuses(
            @Param("busNumber") String busNumber,
            @Param("source") String source,
            @Param("destination") String destination
    );

    List<Bus> findBySourceAndDestinationAndTravelDate(
            String source,
            String destination,
            LocalDate travelDate
    );
}

