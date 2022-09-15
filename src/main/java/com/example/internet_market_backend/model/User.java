package com.example.internet_market_backend.model;

import com.example.internet_market_backend.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String password;
    private Set<Role> roles;
    private Set<Dish> cart;
}
