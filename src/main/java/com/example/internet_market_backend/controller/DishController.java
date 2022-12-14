package com.example.internet_market_backend.controller;

import com.example.internet_market_backend.model.Dish;
import com.example.internet_market_backend.service.interfaces.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@CrossOrigin(origins = "*")
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

    @GetMapping("category")
    public ResponseEntity<List<Dish>> findDishesByCategory(@RequestParam(value = "category") String category,
                                                                 @RequestParam(value = "limit", required = false)
                                                                         Integer limit,
                                                                 @RequestParam(value = "page", required = false,
                                                                         defaultValue = "1")
                                                                         Integer page) {
        List<Dish> response = dishService.findByCategory(category);

        return dishService.buildPaginatedResponse(response, limit, page, String.valueOf(response.size()));
    }

    @PostMapping()
    public ResponseEntity<Dish> saveDish(@RequestBody Dish dish) {
        Dish response = dishService.save(dish);

        return ResponseEntity.ok(response);
    }

    @PutMapping("{dishId}")
    public ResponseEntity<Dish> updateDishImage(@PathVariable Long dishId, @RequestParam("file") MultipartFile file) {
        Dish response = dishService.updateDishImage(dishId, file);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{dishId}")
    public ResponseEntity<Long> deleteDish(@PathVariable Long dishId){
        Long deletedId = dishService.deleteDish(dishId);

        return ResponseEntity.ok(deletedId);
    }
}
