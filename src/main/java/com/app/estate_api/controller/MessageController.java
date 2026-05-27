package com.app.estate_api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estate_api.dto.MessageCreateDto;
import com.app.estate_api.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    
    @Autowired
    private MessageService messageService;

    @PostMapping("")
    public ResponseEntity<HashMap<String, String>> create(@RequestBody MessageCreateDto messageCreateDto) throws Exception {
        
        messageService.createMessage(messageCreateDto);
        HashMap<String, String> message = new HashMap<String, String>();
        message.put("message", "Message send with success");
        return new ResponseEntity<HashMap<String, String>>(message, HttpStatus.CREATED);
    }
}
