package com.example.OnlineTicket.DTO;

import com.example.OnlineTicket.model.Role;
import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Role role;

}
