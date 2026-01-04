package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
