package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.dto.UpdatedUserDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface UserMapper {

    UserDto userToDto(User user);
    User userDtoToUser(UserDto userDto);

    SimpleUserDto userToSimpleDto(User user);
    User simpleUserDtoToUser(SimpleUserDto simpleUserDto);

    User updatedUserDtoToUser(UpdatedUserDto updatedUserDto);
    UpdatedUserDto userToUpdatedUserDto(User user);

}
