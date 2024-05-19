package com.openclassrooms.mddapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class SimpleUserDto {
    @NotBlank
    private String id;
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
}
