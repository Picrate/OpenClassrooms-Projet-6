package com.openclassrooms.mddapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class PostDto extends SimplePostDto{
    @NotBlank
    private String id;
    @NotBlank
    private SimpleUserDto author;
    @NotBlank
    private String createdAt;
    private List<CommentDto> comments;
}
