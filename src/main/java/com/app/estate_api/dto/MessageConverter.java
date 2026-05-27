package com.app.estate_api.dto;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.estate_api.model.Message;
import com.app.estate_api.model.Rental;
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
        if (messageCreateDto.getWriterId() != null) {
            message.setWriter(getWriter(messageCreateDto.getWriterId()));
        }
        if (messageCreateDto.getRentalId() != null) {
            message.setRental(getRental(messageCreateDto.getRentalId()));
        }
    }

    public MessageCreateDto convertToMessageCreateDto(Message message) {

        MessageCreateDto messageCreateDto = new MessageCreateDto();
        
        messageCreateDto.setId(message.getId());
        messageCreateDto.setMessage(message.getMessage());

        messageCreateDto.setWriterId(message.getWriter().getId());
        messageCreateDto.setRentalId(message.getRental().getId());

        return messageCreateDto;
    }

    private User getWriter(Integer writerId) throws Exception
    {
        Optional<User> writerOpt = userService.getUser(writerId);

        if (writerOpt.isPresent())
        {
            return writerOpt.get();
        }
        throw new Exception("Writer not found with id [" + writerId + "]");
    }
    
    private Rental getRental(Integer rentalId) throws Exception
    {
        Optional<Rental> rentalOpt = rentalService.getRental(rentalId);

        if (rentalOpt.isPresent())
        {
            return rentalOpt.get();
        }
        throw new Exception("rental not found with id [" + rentalId + "]");
    }
}
