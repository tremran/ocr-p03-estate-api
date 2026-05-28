package com.app.estate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estate_api.dto.UserResponseDto;
import com.app.estate_api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "manage users - a valid jwt auth token is required ")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{user_id}")
    @Operation(summary="gets user information for given id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class)) }),
        @ApiResponse(responseCode = "400", description = "invalid user id supplied", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "401", description = "missing token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "403", description = "invalid token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "404", description = "user not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
	})
    public ResponseEntity<UserResponseDto> read(@PathVariable("user_id") final Integer id) throws Exception{
        UserResponseDto user = userService.getUserResponseDto(id);
        return ResponseEntity.ok(user);
    }

}
