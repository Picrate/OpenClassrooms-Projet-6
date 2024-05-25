package com.openclassrooms.mddapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto  extends SimpleUserDto{
    private List<TopicDto> topics;
    private String updatedAt;
    private String createdAt;
}
