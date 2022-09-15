package com.example.internet_market_backend.service.interfaces;

import com.example.internet_market_backend.entity.DishEntity;
import com.example.internet_market_backend.entity.UserEntity;
import com.example.internet_market_backend.model.User;
import com.example.internet_market_backend.pojo.JwtResponse;
import com.example.internet_market_backend.pojo.LoginRequest;

import java.util.List;

public interface UserService {
    List<User> findAll();

    UserEntity findById(Long id);

    User findByEmail(String email);

    User save(User user);

    User logIn(String email, String password);

    User updateUserCart(User userEntity, Long dishId);
}
