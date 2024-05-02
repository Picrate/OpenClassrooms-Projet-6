package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto userToDto(User user);
    User userDtoToUser(UserDto userDto);
}
