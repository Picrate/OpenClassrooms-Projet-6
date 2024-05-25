package com.openclassrooms.mddapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostCommentDto {
    @NotBlank
    private String postId;
    @NotBlank
    private String comment;
}
