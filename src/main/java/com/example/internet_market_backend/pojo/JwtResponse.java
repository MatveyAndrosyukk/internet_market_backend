package com.example.internet_market_backend.pojo;

import com.example.internet_market_backend.model.Dish;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String email;
    private Set<String> roles;
    private Set<Dish> cart;

    public JwtResponse(String token, Long id, String name, String email, Set<String> roles, Set<Dish> cart) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.cart = cart;
    }
}
