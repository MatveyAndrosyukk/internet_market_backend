package com.example.internet_market_backend.controller;

import com.example.internet_market_backend.model.User;
import com.example.internet_market_backend.pojo.JwtResponse;
import com.example.internet_market_backend.pojo.LoginRequest;
import com.example.internet_market_backend.pojo.SignUpRequest;
import com.example.internet_market_backend.service.interfaces.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> authorize(@RequestBody LoginRequest loginRequest){
        JwtResponse response = jwtService.authorize(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signupRequest) {
        User response = jwtService.register(signupRequest);
        return ResponseEntity.ok(response);
    }
}
