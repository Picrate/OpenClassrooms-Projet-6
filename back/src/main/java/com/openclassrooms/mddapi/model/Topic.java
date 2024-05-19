package com.openclassrooms.mddapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "topics")
public class Topic {
    public String title;
    public String description;
}
