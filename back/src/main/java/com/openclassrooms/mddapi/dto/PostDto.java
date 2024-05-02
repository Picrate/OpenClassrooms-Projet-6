package com.openclassrooms.mddapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostDto {

    private String id;
    private String title;
    private String content;
    private String topic;
    private SimpleUserDto author;
    private String created_at;
    private List<CommentDto> comments;
}
