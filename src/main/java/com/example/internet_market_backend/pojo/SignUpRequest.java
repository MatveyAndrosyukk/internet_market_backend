package com.example.internet_market_backend.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
}
