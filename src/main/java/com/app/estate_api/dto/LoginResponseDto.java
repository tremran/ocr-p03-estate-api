package com.app.estate_api.dto;

import lombok.Data;

@Data
public class LoginResponseDto implements LoginResponseDtoInterface {
    private String token;

    private long expiresIn;
}
