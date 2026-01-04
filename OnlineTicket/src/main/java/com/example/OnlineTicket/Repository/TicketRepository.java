package com.example.OnlineTicket.Repository;

import com.example.OnlineTicket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

}
