package com.app.estate_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.estate_api.dto.MessageConverter;
import com.app.estate_api.dto.MessageCreateDto;
import com.app.estate_api.model.Message;
import com.app.estate_api.repository.MessageRepository;

import lombok.Data;

@Data
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageConverter converter;

    public Message createMessage(MessageCreateDto messageDto) throws Exception {

        Message savedMessage = converter.createFromMessageCreateDto(messageDto);
        messageRepository.save(savedMessage);

        return savedMessage;
    }

}
