package com.example.internet_market_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException{
    @Serial
    private final static long serialVersionUID = 1L;

    public DataNotFoundException(String message){
        super(message);
    }
}
