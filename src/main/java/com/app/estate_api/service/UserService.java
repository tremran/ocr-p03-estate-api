package com.app.estate_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.estate_api.dto.UserConverter;
import com.app.estate_api.dto.UserResponseDto;
import com.app.estate_api.model.User;
import com.app.estate_api.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter converter;


    public List<User> getUserList() {
        List<User> users = new ArrayList<>();

         userRepository.findAll().forEach(users::add);

         return users;
    }
    public List<UserResponseDto> getUserResponseDtoList(){
        Iterable<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseList = converter.convertListToUserResponseDto(userList);

        return userResponseList;
    }

    public UserResponseDto getUserResponseDto(Integer id) throws Exception {
        User user = getUser(id);

        return converter.convertToUserResponseDto(user);
    }

    public User getUser(final Integer id) throws Exception{
        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isPresent())
        {
            return optUser.get();
        }
        /* TODO attraper l'exception IllegalArgumentException
        et renvoyer une Exception UserNotFoundException
        et la convertir en page 404 dans le globalExceptionHandler
        */
        throw new Exception("User [" + id + "] not found");
    }

}
