package com.app.estate_api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estate_api.dto.MessageCreateDto;
import com.app.estate_api.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Message", description = "manage messages - a valid jwt auth token is required ")
public class MessageController {
    
    @Autowired
    private MessageService messageService;

    @PostMapping("")
    @Operation(summary="adds a message from a user about a rental")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageCreateDto.class)) }),
        @ApiResponse(responseCode = "401", description = "missing token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "403", description = "invalid token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "404", description = "user not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
	})
    public ResponseEntity<HashMap<String, String>> create(@RequestBody MessageCreateDto messageCreateDto) throws Exception {
        
        messageService.createMessage(messageCreateDto);
        HashMap<String, String> message = new HashMap<String, String>();
        message.put("message", "Message send with success");
        return new ResponseEntity<HashMap<String, String>>(message, HttpStatus.CREATED);
    }
}
