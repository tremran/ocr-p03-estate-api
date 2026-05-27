package com.app.estate_api.dto;

import lombok.Data;

@Data
public class UserResponseDto {

    private Integer id;
    private String email;
    private String name;
    private String created_at;
    private String udpated_at;
    
}
