package com.app.estate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estate_api.model.Message;
import com.app.estate_api.dto.MessageCreateDto;
import com.app.estate_api.service.MessageService;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
    
    @Autowired
    private MessageService messageService;

    @PostMapping("")
    public ResponseEntity<Message> create(@RequestBody MessageCreateDto messageCreateDto) throws Exception {
        
        Message message = messageService.createMessage(messageCreateDto);
        return new ResponseEntity<Message>(message, HttpStatus.CREATED);
    }
}
