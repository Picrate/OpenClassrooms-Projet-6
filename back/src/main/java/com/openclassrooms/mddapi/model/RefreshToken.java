package com.openclassrooms.mddapi.model;

import com.openclassrooms.mddapi.dto.SimpleUserDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "refresh_tokens")
public class RefreshToken {
    @Id
    private String id;
    private SimpleUserDto user;
    private String token;
    private Instant expiryDate;
}
