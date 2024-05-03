package com.openclassrooms.mddapi.model;


import com.openclassrooms.mddapi.dto.SimpleUserDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private String title;
    private String content;
    private SimpleUserDto author;
    private String topic;
    private List<Comment> comments;
    @Field("created_at")
    private LocalDateTime createdAt;

    public Post() {
        this.comments = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }
    // Helpers
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
    public List<Comment> getComments() {
        Comparator<Comment> commentComparator = (c1, c2) -> c1.getCreatedAt().compareTo(c2.getCreatedAt());
        return comments.stream().sorted(commentComparator.reversed()).collect(Collectors.toList());
    }
}
