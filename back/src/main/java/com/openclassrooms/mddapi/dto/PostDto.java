package com.openclassrooms.mddapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class PostDto extends SimplePostDto{
    @NotBlank
    private String id;
    @NotBlank
    private SimpleUserDto author;
    @NotBlank
    private String created_at;
    private List<CommentDto> comments;
}
