package com.openclassrooms.mddapi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

  private String type = "Bearer";
  private String refreshToken;
  private UserInfoResponse user;

  public JwtResponse(String refreshToken, UserInfoResponse user) {
    this.refreshToken = refreshToken;
    this.user = user;
  }
}
