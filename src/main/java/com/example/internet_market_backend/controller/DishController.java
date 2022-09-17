package com.example.internet_market_backend.controller;

import com.example.internet_market_backend.model.Dish;
import com.example.internet_market_backend.service.interfaces.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@CrossOrigin(origins = "http://localhost:3000/", exposedHeaders = "x-total-count")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes(@RequestParam(value = "limit", required = false)
                                                                 Integer limit,
                                                         @RequestParam(value = "page", required = false, defaultValue = "1")
                                                                 Integer page) {
        List<Dish> response = dishService.findAll();

        return dishService.buildPaginatedResponse(response, limit, page, String.valueOf(response.size()));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Dish>> findDishesByCategory(@RequestParam(value = "category") String category,
                                                                 @RequestParam(value = "limit", required = false)
                                                                         Integer limit,
                                                                 @RequestParam(value = "page", required = false,
                                                                         defaultValue = "1")
                                                                         Integer page) {
        List<Dish> response = dishService.findByCategory(category);

        return dishService.buildPaginatedResponse(response, limit, page, String.valueOf(response.size()));
    }
}
