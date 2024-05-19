package com.openclassrooms.mddapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostCommentDto {
    @NotBlank
    private String post_id;
    @NotBlank
    private String comment;
}
