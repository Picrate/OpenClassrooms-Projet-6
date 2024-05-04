package com.openclassrooms.mddapi.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
