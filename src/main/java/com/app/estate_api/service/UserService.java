package com.app.estate_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.estate_api.model.User;
import com.app.estate_api.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(final Integer id) {
        return userRepository.findById(id);
    }

    public List<User> getUserList() {
        List<User> users = new ArrayList<>();

         userRepository.findAll().forEach(users::add);

         return users;
    }


}
