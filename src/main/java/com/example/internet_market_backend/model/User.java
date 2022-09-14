package com.example.internet_market_backend.model;

import com.example.internet_market_backend.entity.DishEntity;
import com.example.internet_market_backend.entity.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Set<Role> roles;
    private Set<Dish> cart;
}
