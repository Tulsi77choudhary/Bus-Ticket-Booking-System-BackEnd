package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AuthResponse {
    private User user;
    private String token;
    private String message;

    public AuthResponse(String token, String message, User user) {
        this.token= token;
        this.user = user;
        this.message = message;
    }
}
