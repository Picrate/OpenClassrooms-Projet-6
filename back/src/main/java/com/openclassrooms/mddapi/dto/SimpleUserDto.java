package com.openclassrooms.mddapi.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SimpleUserDto {

    @NotBlank
    private String id;
    @Size(min = 8)
    private String username;
    @Email
    private String email;

}
