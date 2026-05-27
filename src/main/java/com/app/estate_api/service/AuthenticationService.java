package com.app.estate_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.estate_api.dto.UserLoginResponseDto;
import com.app.estate_api.dto.UserRegisterDto;
import com.app.estate_api.exception.BadRequestException;
import com.app.estate_api.model.User;
import com.app.estate_api.repository.UserRepository;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    public User registerUser(UserRegisterDto input) throws Exception{
        User user = new User();
        if (input.getName() == null || input.getName().isBlank()) {
            throw new BadRequestException("Name is missing" );
        }
        if (input.getEmail() == null || input.getEmail().isBlank()) {
            throw new BadRequestException("Email is missing");
        }
        if (input.getPassword() == null || input.getPassword().isBlank()) {
            throw new BadRequestException("Password is missing");
        }

        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(UserLoginResponseDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}