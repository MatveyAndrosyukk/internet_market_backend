package com.example.internet_market_backend.controller;

import com.example.internet_market_backend.model.User;
import com.example.internet_market_backend.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> response = userService.findAll();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User response = userService.findByEmail(email);

        return ResponseEntity.ok(response);
    }

    @GetMapping("login")
    public ResponseEntity<User> logIn(@RequestParam(name = "email") String email,
                                      @RequestParam(name = "password") String password) {
        User response = userService.logIn(email, password);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User response = userService.save(user);

        return ResponseEntity.ok(response);
    }

    @PutMapping("cart/{dishId}")
    public ResponseEntity<User> updateUserCart(@PathVariable Long dishId,
                                               @RequestBody User userEntity) {
        User response = userService.updateUserCart(userEntity, dishId);

        return ResponseEntity.ok(response);
    }

}
