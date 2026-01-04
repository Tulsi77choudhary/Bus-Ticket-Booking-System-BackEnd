package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.SignupRequest;
import com.example.OnlineTicket.model.User;

import java.util.Optional;

public interface UserService {
    User signup(SignupRequest request) ;

    User login(String name, String password);


    void deleteUser(Long id);
    Optional<User> findByEmail(String email);
    User updateUser(Long id, User updatedUser);

    User findById(Long id);
}
