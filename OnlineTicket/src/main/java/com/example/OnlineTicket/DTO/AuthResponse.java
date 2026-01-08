package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.User;
import lombok.Data;
@Data
public class AuthResponse {
    private User user;
    private String token;
    private String message;

    public AuthResponse(String token, String message, User user) {
        this.user = user;
        this.token= token;
        this.message = message;
    }
}
