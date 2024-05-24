package com.openclassrooms.mddapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class SimpleUserDto {

    @NotBlank
    private String id;
    @Size(min = 8)
    private String username;
    @Email
    private String email;

}
