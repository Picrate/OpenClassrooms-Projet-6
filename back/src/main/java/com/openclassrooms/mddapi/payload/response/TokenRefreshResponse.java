package com.openclassrooms.mddapi.payload.response;

import lombok.Data;

@Data
public class TokenRefreshResponse {
    private String refreshToken;
    private final String tokenType = "Bearer";

    public TokenRefreshResponse(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
