package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SimpleUserDto {
    @NotBlank
    private String id;
    @NotBlank
    @Size(min = 6, max = 50)
    private String username;
    @NotBlank
    @Email
    private String email;
}
