package com.openclassrooms.mddapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TopicDto {
    @NotBlank
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
