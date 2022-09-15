package com.example.internet_market_backend.model;

import com.example.internet_market_backend.entity.DishEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dishes_model")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String image;
    private Long price;
    private String title;
    private String description;
    private String category;
    private Long count;
    @Column(name = "total_price")
    private Long totalPrice;
}
