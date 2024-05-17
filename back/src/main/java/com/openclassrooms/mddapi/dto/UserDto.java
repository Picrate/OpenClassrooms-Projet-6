package com.openclassrooms.mddapi.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserDto  extends SimpleUserDto{
    private List<TopicDto> topics;
    private String updatedAt;
    private String createdAt;
}
