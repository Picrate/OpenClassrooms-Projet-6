package com.openclassrooms.mddapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private User author;
    private String content;
    private String createdAt;
}
