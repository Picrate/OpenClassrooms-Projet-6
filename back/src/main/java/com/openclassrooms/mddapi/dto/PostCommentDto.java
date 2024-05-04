package com.openclassrooms.mddapi.dto;

import lombok.Data;

@Data
public class PostCommentDto {
    private String post_id;
    private String comment;
}
