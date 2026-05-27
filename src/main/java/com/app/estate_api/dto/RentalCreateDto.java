package com.app.estate_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class RentalCreateDto {
    private Integer id;
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    private Integer owner_id;
    private String created_at;
    private String updated_at;

    @JsonIgnore
    public Integer getOwnerId(){
        return owner_id;
    }
    public void setOwnerId(Integer ownerId)
    {
        owner_id = ownerId;
    }
}
