package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable String id){
        UserDto dto = userService.getUserDtoById(id);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }else
            return ResponseEntity.ok(dto);
    }

}
