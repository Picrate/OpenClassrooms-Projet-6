package com.openclassrooms.mddapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimplePostDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String topic;
}
