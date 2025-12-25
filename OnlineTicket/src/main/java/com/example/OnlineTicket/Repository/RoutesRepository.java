package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.model.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoutesRepository extends JpaRepository<Routes, Long> {
    List<Routes> findBySourceAndDestinationAndDate(String source, String destination, LocalDate Date);

}

