package com.app.estate_api.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.estate_api.model.Message;
import com.app.estate_api.model.User;
import com.app.estate_api.service.RentalService;
import com.app.estate_api.service.UserService;

@Service
public class MessageConverter {
    @Autowired
    private UserService userService;
    @Autowired
    private RentalService rentalService;

    public Message createFromMessageCreateDto(MessageCreateDto messageCreateDto) throws Exception
    {
        Message message = new Message();

        message.setCreatedAt(new Date());
        this.updateFromMessageCreateDto(message, messageCreateDto);

        return message;
    }
    public void updateFromMessageCreateDto(Message message, MessageCreateDto messageCreateDto) throws Exception
    {
        message.setUpdatedAt(new Date());
        
        if (messageCreateDto.getMessage() != null) {
            message.setMessage(messageCreateDto.getMessage());
        }
        if (messageCreateDto.getUser_id() != null) {
            message.setWriter(getWriter(messageCreateDto.getUser_id()));
        }
        if (messageCreateDto.getRental_id() != null) {
            message.setRental(rentalService.getRental(messageCreateDto.getRental_id()));
        }
    }

    public MessageCreateDto convertToMessageCreateDto(Message message) {

        MessageCreateDto messageCreateDto = new MessageCreateDto();
        
        messageCreateDto.setId(message.getId());
        messageCreateDto.setMessage(message.getMessage());

        messageCreateDto.setUser_id(message.getWriter().getId());
        messageCreateDto.setRental_id(message.getRental().getId());

        return messageCreateDto;
    }

    private User getWriter(Integer writerId) throws Exception
    {
        return userService.getUser(writerId);

    }
    
}
