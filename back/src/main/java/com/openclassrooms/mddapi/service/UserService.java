package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserDtoById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return userMapper.userToDto(user.get());
        } else
            return null;
    }

    public SimpleUserDto getSimpleUserDtoByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return userMapper.userToSimpleUser(user.get());
        } else {
            return null;
        }
    }



}
