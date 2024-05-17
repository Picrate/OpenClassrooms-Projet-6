package com.openclassrooms.mddapi.dto;

import lombok.Data;

@Data
public class CommentDto {
    private String content;
    private SimpleUserDto author;
    private String createdAt;
}
