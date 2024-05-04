package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.dto.UpdatedUserDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserDtoByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
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

    public void updateUser(UpdatedUserDto currentUser) {
        Optional<User> user = this.userRepository.findByEmail(currentUser.getEmail());
        if (user.isPresent()) {
            User userToUpdate = user.get();
            userToUpdate.setEmail(currentUser.getEmail());
            userToUpdate.setUsername(currentUser.getUsername());
            userToUpdate.setPassword(passwordEncoder.encode(currentUser.getPassword()));
            userToUpdate.setUpdatedAt(LocalDateTime.now());
            userToUpdate.setTopics(currentUser.getTopics());
            userRepository.save(userToUpdate);
        }
    }

    public List<String> getUserTopics(String email){
        Optional<User> opt = this.userRepository.findByEmail(email);
        if (opt.isPresent()) {
            return opt.get().getTopics();
        } else {
            return null;
        }
    }

    public Boolean updateTopics(String topic, String email) {
        Optional<User> opt = this.userRepository.findByEmail(email);
        if (opt.isPresent()) {
            User user = opt.get();
            if (!user.getTopics().contains(topic)) {
                user.getTopics().add(topic);
            } else {
                user.getTopics().remove(topic);
            }
            this.userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
