package com.openclassrooms.mddapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Data
public class UpdatedUserDto extends SimpleUserDto {

    /*
    At least 8 characters long;
    One lowercase, one uppercase, one number and one special character;
    No whitespaces.
   */
    @Size(
            min = 8,
            message = "Password must be 8 chars long"
    )
    @Pattern(
            regexp = "^(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*\\d)(?=\\S*([^\\w\\s]|[_]))\\S{8,}$",
            message = "Password must content at least : 1 Uppercase Letter, 1 MinorCase letter, 1 number and a special character"
    )
    private String password;
}
