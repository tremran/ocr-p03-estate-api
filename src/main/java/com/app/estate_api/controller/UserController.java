package com.app.estate_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estate_api.dto.UserResponseDto;
import com.app.estate_api.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponseDto> read(@PathVariable("user_id") final Integer id) throws Exception{
        UserResponseDto user = userService.getUserResponseDto(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> allUsers() {
        List <UserResponseDto> users = userService.getUserResponseDtoList();

        return ResponseEntity.ok(users);
    }
}
