package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatedUserDto extends UserDto{

    /*
    At least 8 characters long;
    One lowercase, one uppercase, one number and one special character;
    No whitespaces.
   */
    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*\\d)(?=\\S*([^\\w\\s]|[_]))\\S{8,}$")
    private String password;
}
