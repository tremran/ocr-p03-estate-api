package com.app.estate_api.dto;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.estate_api.model.User;

@Service
public class UserConverter {
    // todo récupérer le code de conversion d'un user en userresponsedto

    public UserResponseDto convertToUserResponseDto(User user) {

        UserResponseDto userResponseDto = new UserResponseDto();
        
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());

        Format dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        userResponseDto.setCreated_at(dateFormatter.format(user.getCreatedAt()));
        userResponseDto.setUdpated_at(dateFormatter.format(user.getUpdatedAt()));

        return userResponseDto;
    }
    
    public List<UserResponseDto> convertListToUserResponseDto(Iterable<User> userList) {
        ArrayList<UserResponseDto> userResponseDtoList = new ArrayList<UserResponseDto>();

        for (User currentUser: userList ) {
            userResponseDtoList.add(convertToUserResponseDto(currentUser));
        }
        return userResponseDtoList;
    }
}
