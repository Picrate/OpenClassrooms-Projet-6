package com.openclassrooms.mddapi.dto;

import lombok.Data;

@Data
public class RefreshTokenDto {
    private String id;
    private SimpleUserDto user;
    private String token;
    private String expiryDate;
}
