package com.openclassrooms.mddapi.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class SimpleUserDto {
    @NotBlank
    private String id;
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
}
