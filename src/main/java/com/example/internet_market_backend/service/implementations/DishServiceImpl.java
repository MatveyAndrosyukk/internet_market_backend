package com.example.internet_market_backend.service.implementations;

import com.example.internet_market_backend.entity.DishEntity;
import com.example.internet_market_backend.exceptions.DataNotFoundException;
import com.example.internet_market_backend.exceptions.OperationFailedException;
import com.example.internet_market_backend.model.Dish;
import com.example.internet_market_backend.repository.DishRepository;
import com.example.internet_market_backend.service.interfaces.DishService;
import com.example.internet_market_backend.utils.ModelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    private final String DISH_DOES_NOT_EXISTS = "DishEntity does not exists";
    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> findAll() {
        List<DishEntity> dishes;
        try {
            dishes = dishRepository.findAll();
        } catch (Exception ex) {
            throw new OperationFailedException("Find all dishes method failed");
        }

        return ModelUtils.buildDishesList(dishes);
    }

    @Override
    public DishEntity findById(Long id) {
        DishEntity dish = dishRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(DISH_DOES_NOT_EXISTS));

        return dish;
    }

    @Override
    public List<Dish> findByCategory(String category) {
        List<DishEntity> dishes;
        try {
            dishes = dishRepository.findByCategory(category);
        } catch (Exception ex) {
            throw new OperationFailedException("Find dish by category method failed");
        }

        return ModelUtils.buildDishesList(dishes);
    }

    @Override
    public Dish save(Dish dish) {
        DishEntity dishEntity = ModelUtils.buildDishEntity(dish);

        DishEntity savedDish;
        try {
            savedDish = dishRepository.save(dishEntity);
        } catch (Exception ex) {
            throw new OperationFailedException("Save dish method failed");
        }

        return ModelUtils.buildDish(savedDish);
    }

    @Override
    public Dish updateDishImage(Long dishId, MultipartFile file) {
        DishEntity dishById = findById(dishId);

        try{
            dishById.setImage(file.getBytes());
        }catch (Exception e) {
            throw new OperationFailedException("Decoding dish image failed!");
        }

        DishEntity updatedDish;
        try {
            updatedDish = dishRepository.save(dishById);
        } catch (Exception ex) {
            throw new OperationFailedException("Update dish image method failed!");
        }

        return ModelUtils.buildDish(updatedDish);
    }

    @Override
    public Long deleteDish(Long dishId) {
        try {
            dishRepository.deleteById(dishId);
        }catch (Exception ex){
            throw new OperationFailedException("Delete dish method failed!");
        }

        return dishId;
    }

    @Override
    public ResponseEntity<List<Dish>> buildPaginatedResponse(List<Dish> dishes,
                                                                   Integer limit,
                                                                   Integer page,
                                                                   String header) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", header);

        return new ResponseEntity<>(limit == null || limit == -1
                ? dishes
                : dishes.stream().skip((long) (page - 1) * limit).limit(limit).collect(Collectors.toList()),
                headers,
                HttpStatus.OK);
    }
}
