package com.example.internet_market_backend.controller;

import com.example.internet_market_backend.model.Dish;
import com.example.internet_market_backend.service.interfaces.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000/")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final DishService dishService;

    public AdminController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("dishes")
    public ResponseEntity<Dish> saveDish(@RequestBody Dish dish) {
        Dish response = dishService.save(dish);

        return ResponseEntity.ok(response);
    }

    @PutMapping("dishes/{dishId}")
    public ResponseEntity<Dish> updateDishImage(@PathVariable Long dishId, @RequestParam("file") MultipartFile file) {
        Dish response = dishService.updateDishImage(dishId, file);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("dishes/{dishId}")
    public ResponseEntity<Long> deleteDish(@PathVariable Long dishId){
        Long deletedId = dishService.deleteDish(dishId);

        return ResponseEntity.ok(deletedId);
    }
}
