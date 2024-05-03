package com.openclassrooms.mddapi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private String id;
  private String username;
  private String email;

  private Boolean admin;

  public JwtResponse(String accessToken, String email) {
    this.token = accessToken;
    this.email = email;
  }
}
