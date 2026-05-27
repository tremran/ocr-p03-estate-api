package com.app.estate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estate_api.dto.LoginErrorResponseDto;
import com.app.estate_api.dto.LoginResponseDto;
import com.app.estate_api.dto.LoginResponseDtoInterface;
import com.app.estate_api.dto.UserConverter;
import com.app.estate_api.dto.UserLoginResponseDto;
import com.app.estate_api.dto.UserRegisterDto;
import com.app.estate_api.dto.UserResponseDto;
import com.app.estate_api.model.User;
import com.app.estate_api.service.AuthenticationService;
import com.app.estate_api.service.JwtService;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserConverter converter;
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register(@RequestBody UserRegisterDto userRegisterDto) throws Exception {
        User registeredUser = authenticationService.registerUser(userRegisterDto);

        String jwtToken = jwtService.generateToken(registeredUser);

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDtoInterface> authenticate(@RequestBody UserLoginResponseDto userloginDto) {
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

        // Message message = messageService.createMessage(messageCreateDto);
        // return new ResponseEntity<Message>(message, HttpStatus.CREATED);
            return new ResponseEntity<LoginResponseDtoInterface>(response, HttpStatus.UNAUTHORIZED);
            
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(converter.convertToUserResponseDto(currentUser));
    }

}