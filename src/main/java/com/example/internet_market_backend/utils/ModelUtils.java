package com.example.internet_market_backend.utils;

import com.example.internet_market_backend.entity.DishEntity;
import com.example.internet_market_backend.entity.Role;
import com.example.internet_market_backend.entity.UserEntity;
import com.example.internet_market_backend.model.Dish;
import com.example.internet_market_backend.model.User;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.*;

public class ModelUtils {
    public static List<User> buildUserList(List<UserEntity> users) {
        List<User> response = new ArrayList<>();

        users.forEach(userEntity -> {
            Set<Dish> cart = buildUserCart(userEntity);

            User user = User.builder()
                    .id(userEntity.getId())
                    .name(userEntity.getName())
                    .email(userEntity.getEmail())
                    .phone(userEntity.getPhone())
                    .password(userEntity.getPassword())
                    .roles(userEntity.getRoles())
                    .cart(cart)
                    .build();

            response.add(user);
        });

        return response;
    }

    public static User buildUser(UserEntity userEntity) {
        Set<Dish> cart = buildUserCart(userEntity);

        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .password(userEntity.getPassword())
                .roles(userEntity.getRoles())
                .cart(cart)
                .build();
    }

    public static UserEntity buildClearUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .roles(Collections.singleton(Role.USER))
                .cart(new HashSet<>())
                .build();
    }

    private static Set<Dish> buildUserCart(UserEntity userEntity) {
        Set<Dish> cart = new HashSet<>();

        userEntity.getCart().forEach(dishEntity -> {
            Dish dish = buildDish(dishEntity);

            cart.add(dish);
        });

        return cart;
    }

    public static List<Dish> buildDishesList(List<DishEntity> dishEntityList) {
        List<Dish> dishes = new ArrayList<>();

        dishEntityList.forEach(dishEntity -> {
            Dish dish = buildDish(dishEntity);

            dishes.add(dish);
        });

        return dishes;
    }

    public static DishEntity buildDishEntity(Dish dish) {
        return DishEntity.builder()
                .image(Base64.decodeBase64(dish.getImage().substring(22)))
                .price(dish.getPrice())
                .title(dish.getTitle())
                .description(dish.getDescription())
                .category(dish.getCategory())
                .count(1L)
                .totalPrice(dish.getPrice())
                .build();
    }

    public static Dish buildDish(DishEntity dishEntity) {
        return Dish.builder()
                .id(dishEntity.getId())
                .image(Base64.encodeBase64String(dishEntity.getImage()) != null
                        ? "data:image/png;base64," + Base64.encodeBase64String(dishEntity.getImage())
                        : Base64.encodeBase64String(dishEntity.getImage()))
                .price(dishEntity.getPrice())
                .title(dishEntity.getTitle())
                .description(dishEntity.getDescription())
                .category(dishEntity.getCategory())
                .count(dishEntity.getCount())
                .totalPrice(dishEntity.getTotalPrice())
                .build();
    }
}
