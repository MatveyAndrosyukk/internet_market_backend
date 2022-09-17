package com.example.internet_market_backend.service.interfaces;

import com.example.internet_market_backend.entity.DishEntity;
import com.example.internet_market_backend.model.Dish;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DishService {
    List<Dish> findAll();

    DishEntity findById(Long id);

    List<Dish> findByCategory(String category);

    Dish save(Dish dish);

    Dish updateDishImage(Long id, MultipartFile file);

    Long deleteDish(Long dishId);

    ResponseEntity<List<Dish>> buildPaginatedResponse(List<Dish> dishes,
                                                            Integer limit,
                                                            Integer page,
                                                            String header);
}
