package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.Config.JwtUtil;
import com.example.OnlineTicket.DTO.AuthResponse;
import com.example.OnlineTicket.DTO.SignupRequest;
import com.example.OnlineTicket.Service.UserService;
import com.example.OnlineTicket.model.User;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest signupRequest) throws ExecutionControl.UserException {
        User savedUser = userService.signup(signupRequest);
        String token = jwtUtil.generateToken(savedUser.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(token, "Signup Success"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody SignupRequest loginRequest) throws ExecutionControl.UserException {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, "Login Success"));
    }
}

