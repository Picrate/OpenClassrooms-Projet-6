package com.openclassrooms.mddapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentDto {
    @NotBlank
    private String content;
    private SimpleUserDto author;
    private String createdAt;
}
