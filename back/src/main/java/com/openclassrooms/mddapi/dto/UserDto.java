package com.openclassrooms.mddapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String id;
    private String username;
    private String email;
    private List<String> topics;
    private String updated_at;
    private String created_at;
}
