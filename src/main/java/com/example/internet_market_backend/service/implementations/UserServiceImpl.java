package com.example.internet_market_backend.service.implementations;

import com.example.internet_market_backend.entity.DishEntity;
import com.example.internet_market_backend.entity.UserEntity;
import com.example.internet_market_backend.exceptions.DataNotFoundException;
import com.example.internet_market_backend.exceptions.OperationFailedException;
import com.example.internet_market_backend.model.User;
import com.example.internet_market_backend.repository.UserRepository;
import com.example.internet_market_backend.service.interfaces.DishService;
import com.example.internet_market_backend.service.interfaces.UserService;
import com.example.internet_market_backend.utils.ModelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DishService dishService;

    public UserServiceImpl(UserRepository userRepository, DishService dishService) {
        this.userRepository = userRepository;
        this.dishService = dishService;
    }

    @Override
    public List<User> findAll() {
        log.info("Getting all users");

        List<UserEntity> users;

        try{
            users = userRepository.findAll();
        }catch (Exception ex){
            throw new OperationFailedException("Find All method failed");
        }

        return ModelUtils.buildUserList(users);
    }

    @Override
    public UserEntity findById(Long id) {
        log.info("Getting user by id: {}", id);

        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User does not exists"));
    }

    @Override
    public User findByEmail(String email) {
        log.info("Getting user by email: {}", email);

        UserEntity userEntity;
        try{
            userEntity = userRepository.findByEmail(email);
        }catch (Exception ex){
            throw new OperationFailedException("Getting user by email method failed");
        }

        return userEntity == null ? null : ModelUtils.buildUser(userEntity);
    }

    @Override
    public User save(User user) {
        log.info("Saving user: {}", user);

        UserEntity userEntity = ModelUtils.buildClearUserEntity(user);

        UserEntity savedUser;
        if (findByEmail(userEntity.getEmail()) == null){
            try{
                savedUser = userRepository.save(userEntity);
            }catch (Exception ex){
                throw new OperationFailedException("Save user method failed");

            }
        }else {
            throw new OperationFailedException("UserEntity with this email already exists!");
        }

        return ModelUtils.buildUser(savedUser);
    }

    @Override
    public User logIn(String email, String password) {
        log.info("Finding user with email: {}, password: {}", email, password);

        User userByEmail = findByEmail(email);

        if (userByEmail == null){
            throw new DataNotFoundException("User with this email does not exists");
        }

        if (!userByEmail.getPassword().equals(password)){
            throw new DataNotFoundException("Passwords doesn't match");
        }

        return userByEmail;
    }

    @Override
    public User updateUserCart(User user, Long dishId) {
        log.info("Updating user's cart");

        DishEntity dishEntityById = dishService.findById(dishId);
        UserEntity userEntityById = findById(user.getId());

        if (userEntityById.getCart().contains(dishEntityById)){
            userEntityById.getCart().remove(dishEntityById);
        }else {
            userEntityById.getCart().add(dishEntityById);
        }

        UserEntity savedUser;
        try {
           savedUser = userRepository.save(userEntityById);
        } catch (Exception e) {
            throw new OperationFailedException("Save user method failed");
        }
        return ModelUtils.buildUser(savedUser);
    }
}
