package com.openclassrooms.mddapi.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private String title;
    private String content;
    private User author;
    private String topic;
    private List<Comment> comments;
    @Field("created_at")
    private String createdAt;

    public Post() {
        this.comments = new ArrayList<>();
    }
}
