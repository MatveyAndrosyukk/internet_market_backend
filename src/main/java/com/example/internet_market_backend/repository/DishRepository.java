package com.example.internet_market_backend.repository;

import com.example.internet_market_backend.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<DishEntity, Long> {
    List<DishEntity> findByCategory(String category);
    Optional<DishEntity> findById(Long id);
}
