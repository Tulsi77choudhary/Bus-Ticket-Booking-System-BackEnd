package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.DTO.PassengerDto;
import com.example.OnlineTicket.model.Booking;
import com.example.OnlineTicket.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PassengerRepository extends JpaRepository<Passenger,Long> {

}
