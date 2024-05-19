package com.openclassrooms.mddapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TopicDto {
    @NotBlank
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
