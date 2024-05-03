package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.UpdatedUserDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserProfile(){
        String currentPrincipalName = getCurrentPrincipalName(SecurityContextHolder.getContext());
        UserDto dto = userService.getUserDtoByEmail(currentPrincipalName);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }else
            return ResponseEntity.ok(dto);
    }

    @PutMapping("/me")
    public ResponseEntity<MessageResponse> updateMyProfile(@Valid @RequestBody UpdatedUserDto updatedUserDto){
        String currentPrincipalName = getCurrentPrincipalName(SecurityContextHolder.getContext());
        UserDto currentUser = userService.getUserDtoByEmail(currentPrincipalName);
        if(currentUser == null){
            return ResponseEntity.notFound().build();
        } else if(updatedUserDto.getId().equals(currentUser.getId())){
            this.userService.updateUser(updatedUserDto);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Not allowed to update profile."));
        }
    }

    @GetMapping("/topics")
    public ResponseEntity<List<String>> getUserTopics(){
        String currentPrincipalName = getCurrentPrincipalName(SecurityContextHolder.getContext());
        List<String> topics = userService.getUserTopics(currentPrincipalName);
        if(topics == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(topics);
        }
    }

    @PutMapping("/topics")
    public ResponseEntity<MessageResponse> updateUserTopics(@Valid @RequestBody TopicDto topic){
        String currentPrincipalName = getCurrentPrincipalName(SecurityContextHolder.getContext());
        if(topic == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Topic can not be empty."));
        } else {
            if(this.userService.updateTopics(topic.getTopic(), currentPrincipalName))
                return ResponseEntity.noContent().build();
            else
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("Error in updating user topics."));
        }
    }

    @GetMapping("/feed")
    public ResponseEntity<List<PostDto>> getUserFeed(){
        String currentPrincipalName = getCurrentPrincipalName(SecurityContextHolder.getContext());
        List<PostDto> posts = this.postService.getAllPostsForTopics(this.userService.getUserTopics(currentPrincipalName));
        return ResponseEntity.ok(posts);
    }


    private String getCurrentPrincipalName(SecurityContext securityContext){
        Authentication authentication = securityContext.getAuthentication();
        return authentication.getName();
    }

}
