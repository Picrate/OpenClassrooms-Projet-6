package com.openclassrooms.mddapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String id;
    private String username;
    private String email;
    private List<String> topics;
}
