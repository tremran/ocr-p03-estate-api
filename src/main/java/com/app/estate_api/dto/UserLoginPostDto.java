package com.app.estate_api.dto;

import lombok.Data;

@Data
public class UserLoginPostDto {    
    private String email;
    private String password;
}
