package com.app.estate_api.dto;

import lombok.Data;

@Data
public class MessageCreateDto {

    private Integer id;
    private String message;
    private Integer rental_id;
    private Integer user_id;

}
