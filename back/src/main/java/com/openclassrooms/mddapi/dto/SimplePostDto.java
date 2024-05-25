package com.openclassrooms.mddapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SimplePostDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private TopicDto topic;
}
