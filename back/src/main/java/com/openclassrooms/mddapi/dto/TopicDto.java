package com.openclassrooms.mddapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TopicDto {
    @NotBlank
    private String topic;
}
