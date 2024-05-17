package com.openclassrooms.mddapi.model;

import com.openclassrooms.mddapi.dto.SimpleUserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment {
    private Author author;
    private String content;
    @Field("created_at")
    private LocalDateTime createdAt;

    public Comment(Author author, String content) {
        this.author = author;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}
