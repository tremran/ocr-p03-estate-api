package com.app.estate_api.dto;

import lombok.Data;

@Data
public class RentalResponseDto {
    private Integer id;
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    private String created_at;
    private String updated_at;
    private Integer owner_id;

}
