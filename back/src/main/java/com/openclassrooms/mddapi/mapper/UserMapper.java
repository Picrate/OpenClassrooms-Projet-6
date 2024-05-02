package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "created_at", source = "createdAt")
    @Mapping(target = "updated_at", source = "updatedAt")
    UserDto userToDto(User user);
    User userDtoToUser(UserDto userDto);
    SimpleUserDto userToSimpleUser(User user);
}
