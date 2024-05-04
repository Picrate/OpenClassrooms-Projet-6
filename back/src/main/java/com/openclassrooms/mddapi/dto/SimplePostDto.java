package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SimplePostDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String topic;
}
