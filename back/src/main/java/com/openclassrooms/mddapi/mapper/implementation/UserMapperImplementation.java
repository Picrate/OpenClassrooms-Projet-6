package com.openclassrooms.mddapi.mapper.implementation;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;

public class UserMapperImplementation implements UserMapper {

    @Override
    public UserDto userToDto(User user) {
        if(user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setTopics(user.getTopics());
        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        if(userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
