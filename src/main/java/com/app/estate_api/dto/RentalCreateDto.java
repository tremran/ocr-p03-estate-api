package com.app.estate_api.dto;

import lombok.Data;

@Data
public class RentalCreateDto {
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
}
