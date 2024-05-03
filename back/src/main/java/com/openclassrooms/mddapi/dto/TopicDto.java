package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TopicDto {
    @NotBlank
    private String topic;
}
