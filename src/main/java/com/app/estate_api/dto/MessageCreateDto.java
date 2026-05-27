package com.app.estate_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class MessageCreateDto {

    private Integer id;
    private String message;
    private Integer rental_id;
    private Integer writer_id;

    @JsonIgnore
    public Integer getRentalId()
    {
        return rental_id;
    }
    @JsonIgnore
    public void setRentalId(Integer id)
    {
        rental_id = id;
    }
    @JsonIgnore
    public Integer getWriterId()
    {
        return writer_id;
    }
    @JsonIgnore
    public void setWriterId(Integer id)
    {
        writer_id = id;
    }
}
