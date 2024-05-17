package com.openclassrooms.mddapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private String id;
    @NotBlank
    @Size(max=20)
    private String username;
    @NotBlank
    @Size(max=120)
    private String password;
    @NotBlank
    @Size(max=20)
    @Email
    private String email;

    private Set<Role> roles;

    private List<Topic> topics;

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("updated_at")
    private LocalDateTime updatedAt;

    public User() {
        this.topics = new ArrayList<>();
        this.roles = new HashSet<>();
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.topics = new ArrayList<>();
        this.roles =  new HashSet<>();
        this.createdAt = LocalDateTime.now();
    }
}
