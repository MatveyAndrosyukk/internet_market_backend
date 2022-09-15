package com.example.internet_market_backend.service.interfaces;

import com.example.internet_market_backend.model.User;
import com.example.internet_market_backend.pojo.JwtResponse;
import com.example.internet_market_backend.pojo.LoginRequest;
import com.example.internet_market_backend.pojo.SignUpRequest;

public interface JwtService {
    JwtResponse authorize(LoginRequest loginRequest);

    User register(SignUpRequest signUpRequest);
}
