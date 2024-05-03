package com.openclassrooms.mddapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto  extends SimpleUserDto{
    private List<String> topics;
    private String updated_at;
    private String created_at;
}
