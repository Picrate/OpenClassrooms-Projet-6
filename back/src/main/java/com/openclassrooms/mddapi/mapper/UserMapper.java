package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "created_at", source = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    @Mapping(target = "updated_at", source = "updatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    UserDto userToDto(User user);

    User userDtoToUser(UserDto userDto);

    SimpleUserDto userToSimpleUser(User user);
}
