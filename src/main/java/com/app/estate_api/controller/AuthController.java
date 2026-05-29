package com.app.estate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estate_api.dto.LoginErrorResponseDto;
import com.app.estate_api.dto.LoginResponseDto;
import com.app.estate_api.dto.LoginResponseDtoInterface;
import com.app.estate_api.dto.UserConverter;
import com.app.estate_api.dto.UserLoginPostDto;
import com.app.estate_api.dto.UserRegisterDto;
import com.app.estate_api.dto.UserResponseDto;
import com.app.estate_api.model.User;
import com.app.estate_api.service.AuthenticationService;
import com.app.estate_api.service.JwtService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/auth")
@RestController
@OpenAPIDefinition(info = @Info(title="Estate API", description="rentals, users and messages ! all in 1 place"))
@Tag(name = "Authentication", description = "register, log and print the user")
public class AuthController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserConverter converter;
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary="register a user and send a jwt authentification token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "registration done", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) })
	})
    public ResponseEntity<LoginResponseDto> register(@RequestBody UserRegisterDto userRegisterDto) throws Exception {
        User registeredUser = authenticationService.registerUser(userRegisterDto);

        String jwtToken = jwtService.generateToken(registeredUser);

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/login")
    @Operation(summary="log a user and send a jwt authentification token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "login OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class)) }),
        @ApiResponse(responseCode = "401", description = "invalid credentials", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoginErrorResponseDto.class)) })
	})
    public ResponseEntity<LoginResponseDtoInterface> authenticate(@RequestBody UserLoginPostDto userloginDto) {
        try {
            User authenticatedUser = authenticationService.authenticate(userloginDto);
    
            String jwtToken = jwtService.generateToken(authenticatedUser);
    
            LoginResponseDto loginResponse = new LoginResponseDto();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());
    
            return ResponseEntity.ok(loginResponse);
        }
        catch (BadCredentialsException e)
        {
            LoginErrorResponseDto response = new LoginErrorResponseDto("error");

            return new ResponseEntity<LoginResponseDtoInterface>(response, HttpStatus.UNAUTHORIZED);
            
        }
    }

    @GetMapping("/me")
    @Operation(summary="gets user information for logged user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class)) }),
        @ApiResponse(responseCode = "403", description = "invalid token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) })
	})
    public ResponseEntity<UserResponseDto> authenticatedUser() throws Exception {
        User currentUser = authenticationService.getLoggedUser();

        return ResponseEntity.ok(converter.convertToUserResponseDto(currentUser));
    }

}