package com.openclassrooms.mddapi.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "roles")
public class Role {

    @Id
    private String id;
    private ERole name;

    public Role() {
    }
    public Role(ERole name) {
        this.name = name;
    }
}
