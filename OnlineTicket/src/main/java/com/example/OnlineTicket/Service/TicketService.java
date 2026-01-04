package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.model.Ticket;

public interface TicketService {
    Ticket findUserId(Long id);
}
