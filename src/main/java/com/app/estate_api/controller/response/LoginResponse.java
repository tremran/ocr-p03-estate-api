package com.app.estate_api.controller.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    private long expiresIn;
}
