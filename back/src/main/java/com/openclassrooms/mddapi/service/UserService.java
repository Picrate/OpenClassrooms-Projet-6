package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.UpdatedUserDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.ERole;
import com.openclassrooms.mddapi.model.Role;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.RoleRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TopicMapper topicMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, TopicMapper topicMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.topicMapper = topicMapper;
        this.roleRepository = roleRepository;
    }

    public UserDto getUserDtoByEmailOrUsername(String login) {
        Optional<User> user = userRepository.findByEmailOrUsername(login, login);
        if (user.isPresent()) {
            return userMapper.userToDto(user.get());
        } else
            return null;
    }

    public SimpleUserDto getSimpleUserDtoByEmailOrUsername(String login) {
        Optional<User> user = userRepository.findByEmailOrUsername(login, login);
        if (user.isPresent()) {
            return userMapper.userToSimpleDto(user.get());
        } else {
            return null;
        }
    }

    public SimpleUserDto getSimpleUSerDtoById(String userId){
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isPresent()) {
            return userMapper.userToSimpleDto(user.get());
        }
        return null;
    }

    public void updateUser(UpdatedUserDto newUserInfos) {
        Optional<User> user = this.userRepository.findById(newUserInfos.getId());
        if (user.isPresent()) {
            User userToUpdate = user.get();
            if(newUserInfos.getPassword() != null) {
                userToUpdate.setPassword(passwordEncoder.encode(newUserInfos.getPassword()));
            }
            if(newUserInfos.getEmail() != null) {
                userToUpdate.setEmail(newUserInfos.getEmail());
            }
            if(newUserInfos.getUsername() != null) {
                userToUpdate.setUsername(newUserInfos.getUsername());
            }
            userToUpdate.setUpdatedAt(LocalDateTime.now());
            userRepository.save(userToUpdate);
        }
    }

    public List<TopicDto> getUserTopics(String login){
        Optional<User> opt = this.userRepository.findByEmailOrUsername(login, login);
        if (opt.isPresent()) {
            return topicMapper.topicListToTopicDtoList(opt.get().getTopics());
        } else {
            return new ArrayList<>();
        }
    }

    public List<String> getUserRoles(String login){
        List<String> roles = new ArrayList<String>();
        Optional<User> opt = this.userRepository.findByEmailOrUsername(login, login);
        if (opt.isPresent()) {
            User user = opt.get();
            for (Role role : user.getRoles()) {
                roles.add(role.getName().name());
            }
        }
        return roles;
    }

    public Boolean updateTopics(TopicDto topicDto, String login) {
        Optional<User> opt = this.userRepository.findByEmailOrUsername(login, login);
        Topic topicToUpdate = topicMapper.topicDtoToTopic(topicDto);
        if (opt.isPresent()) {
            User user = opt.get();
            List<Topic> userTopics = user.getTopics();
            if (!userTopics.contains(topicToUpdate)) {
                userTopics.add(topicToUpdate);
                user.setTopics(userTopics);
            } else {
                userTopics.remove(topicToUpdate);
                user.setTopics(userTopics);
            }
            this.userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public boolean existsByEmailOrUsername(String login) {return this.userRepository.existsByEmailOrUsername(login, login);}

    public Optional<Role> getRole(ERole role){
        return roleRepository.findByName(role);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }
}
