package com.openclassrooms.mddapi.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Size(min = 6,max = 50)
  private String username;

  /*
    At least 8 characters long;
    One lowercase, one uppercase, one number and one special character;
    No whitespaces.
   */
  @NotBlank
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
