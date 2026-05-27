package com.app.estate_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estate_api.model.User;
import com.app.estate_api.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<User> read(@PathVariable("user_id") final Integer id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent())
        {
            return ResponseEntity.ok(user.get());
        }
        
        return null;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.getUserList();

        return ResponseEntity.ok(users);
    }
}
