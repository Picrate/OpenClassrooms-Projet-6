package com.openclassrooms.mddapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document(collection = "topics")
public class Topic {
    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
