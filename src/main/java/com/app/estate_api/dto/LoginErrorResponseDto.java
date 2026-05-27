package com.app.estate_api.dto;

import lombok.Data;

@Data
public class LoginErrorResponseDto implements LoginResponseDtoInterface {
    private String message;

    public LoginErrorResponseDto(String message)
    {
        this.message = message;
    }
}
