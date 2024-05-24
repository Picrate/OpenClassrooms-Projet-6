package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.*;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4002", maxAge = 3600, allowCredentials="true")
public class UserController {

    private final UserService userService;
    private final PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SimpleUserDto> getUserByEmail(@RequestParam String login){
        if(this.userService.existsByEmailOrUsername(login)){
            return ResponseEntity.ok(this.userService.getSimpleUserDtoByEmailOrUsername(login));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDto> getUserProfile(){
        String currentPrincipalName = getCurrentPrincipalName(SecurityContextHolder.getContext());
        UserDto dto = userService.getUserDtoByEmailOrUsername(currentPrincipalName);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }else
            return ResponseEntity.ok(dto);
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageResponse> updateMyProfile(@Valid @RequestBody UpdatedUserDto updatedUserDto){
        String currentPrincipalName = getCurrentPrincipalName(SecurityContextHolder.getContext());
        UserDto currentUser = userService.getUserDtoByEmailOrUsername(currentPrincipalName);
        if(currentUser == null){
            return ResponseEntity.notFound().build();
        } else if(updatedUserDto.getId().equals(currentUser.getId())){
            this.userService.updateUser(updatedUserDto);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.unprocessableEntity().body(new MessageResponse("Can't update account"));
        }
    }

    @GetMapping("/topics")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TopicDto>> getUserTopics(){
        String currentPrincipalName = getCurrentPrincipalName(SecurityContextHolder.getContext());
        List<TopicDto> topics = userService.getUserTopics(currentPrincipalName);
        if(topics == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(topics);
        }
    }

    @PutMapping("/topics")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageResponse> updateUserTopics(@Valid @RequestBody TopicDto topic){
        String currentPrincipalName = getCurrentPrincipalName(SecurityContextHolder.getContext());
        if(topic == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Topic can not be empty."));
        } else {
            if(Boolean.TRUE.equals(this.userService.updateTopics(topic, currentPrincipalName)))
                return ResponseEntity.noContent().build();
            else
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("Error in updating user topics."));
        }
    }

    @GetMapping("/feed")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<PostDto>> getUserFeed(){
        String currentPrincipalName = getCurrentPrincipalName(SecurityContextHolder.getContext());
        List<PostDto> posts = this.postService.getAllPostsForTopics(this.userService.getUserTopics(currentPrincipalName));
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/exists")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageResponse> isUserExists(@RequestBody @Valid UserExistsRequestDto userDto){
        boolean response = this.userService.existsByEmailOrUsername(userDto.getUsernameOrEmail());
        return ResponseEntity.ok(new MessageResponse(String.valueOf(response)));
    }

    private String getCurrentPrincipalName(SecurityContext securityContext){
        Authentication authentication = securityContext.getAuthentication();
        return authentication.getName();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
