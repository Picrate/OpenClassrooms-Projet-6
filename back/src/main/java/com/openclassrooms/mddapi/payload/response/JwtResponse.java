package com.openclassrooms.mddapi.payload.response;

import com.openclassrooms.mddapi.dto.SimpleUserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

  private String token;
  private String type = "Bearer";
  private SimpleUserDto user;

  public JwtResponse(String accessToken, SimpleUserDto simpleUserDto) {
    this.token = accessToken;
    this.user = simpleUserDto;
  }
}
