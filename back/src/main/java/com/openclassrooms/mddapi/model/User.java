package com.openclassrooms.mddapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private boolean isAdmin;
    private List<String> topics;
    @Field("created_at")
    private LocalDateTime createdAt;
    @Field("updated_at")
    private LocalTime updatedAt;

    public User() {
        this.topics = new ArrayList<>();
    }

    public User(String username, String email, String password, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.topics = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }
}
