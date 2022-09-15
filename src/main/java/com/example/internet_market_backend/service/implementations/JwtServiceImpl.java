package com.example.internet_market_backend.service.implementations;

import com.example.internet_market_backend.entity.Role;
import com.example.internet_market_backend.entity.UserEntity;
import com.example.internet_market_backend.exceptions.OperationFailedException;
import com.example.internet_market_backend.model.User;
import com.example.internet_market_backend.pojo.JwtResponse;
import com.example.internet_market_backend.pojo.LoginRequest;
import com.example.internet_market_backend.pojo.SignUpRequest;
import com.example.internet_market_backend.repository.UserRepository;
import com.example.internet_market_backend.service.interfaces.JwtService;
import com.example.internet_market_backend.utils.JwtUtils;
import com.example.internet_market_backend.utils.ModelUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public JwtServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public JwtResponse authorize(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getEmail(),
                roles,
                userDetails.getCart());
    }

    @Override
    public User register(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new OperationFailedException("User with this email exists");
        }

        UserEntity userEntity = new UserEntity(null,
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getPhone(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                Collections.singleton(Role.USER),
                new HashSet<>()
        );

        UserEntity savedUser;
        try{
            savedUser = userRepository.save(userEntity);
        }catch (Exception e){
            throw new OperationFailedException("Save user method failed!");
        }

        return ModelUtils.buildUser(savedUser);
    }
}
