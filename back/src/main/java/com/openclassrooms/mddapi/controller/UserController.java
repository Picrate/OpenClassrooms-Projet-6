package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UpdatedUserDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDto dto = userService.getUserDtoByEmail(currentPrincipalName);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }else
            return ResponseEntity.ok(dto);
    }

    @PutMapping("/me")
    public ResponseEntity<MessageResponse> updateMyProfile(@Valid @RequestBody UpdatedUserDto updatedUserDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDto currentUser = userService.getUserDtoByEmail(currentPrincipalName);
        if(currentUser == null){
            return ResponseEntity.notFound().build();
        } else if(updatedUserDto.getId().equals(currentUser.getId())){
            this.userService.updateUser(updatedUserDto);
            return ResponseEntity.ok().body(new MessageResponse("User updated successfully"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Not allowed to update profile."));
        }
    }

}
