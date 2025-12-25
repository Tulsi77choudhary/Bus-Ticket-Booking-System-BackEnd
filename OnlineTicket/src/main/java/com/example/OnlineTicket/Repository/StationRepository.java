package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    boolean existsByName(String name);
}

