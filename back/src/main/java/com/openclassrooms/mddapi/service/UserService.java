package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.mapper.implementation.UserMapperImplementation;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public UserDto getUserDtoByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            UserMapper userMapper = new UserMapperImplementation();
            return userMapper.userToDto(user.get());
        } else {
            return null;
        }
    }

    public UserDto getUserDtoById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserMapper userMapper = new UserMapperImplementation();
            return userMapper.userToDto(user.get());
        } else
            return null;
    }
}
