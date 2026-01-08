package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.Repository.UserRepository;
import com.example.OnlineTicket.model.Ticket;
import com.example.OnlineTicket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public Ticket findUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));

        return null;
    }
}
