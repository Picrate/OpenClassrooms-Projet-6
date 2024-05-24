package com.openclassrooms.mddapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserExistsRequestDto {
    @NotBlank
    String usernameOrEmail;
}
