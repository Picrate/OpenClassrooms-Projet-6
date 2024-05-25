package com.openclassrooms.mddapi.security.jwt;

import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    @Value("${oc.app.jwtSecret}")
    private String jwtSecret;

    @Value("${oc.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${oc.app.jwtCookieName}")
    private String jwtCookie;

    @Value("${oc.app.jwtRefreshCookieName}")
    private String jwtRefreshCookie;

    private String generateJwtToken(UserDetailsImpl userDetails) {
        return generateTokenFromUsername(userDetails.getUsername());
    }

    private String generateJwtRefreshToken(UserDetailsImpl userDetails) {
        return generateTokenFromUsername(userDetails.getUsername());
    }

    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getEmail());
        return generateCookie(jwtCookie, jwt, "/api");
    }

    public ResponseCookie generateJwtCookie(SimpleUserDto user) {
        String jwt = generateTokenFromUsername(user.getUsername());
        return generateCookie(jwtCookie, jwt, "/api");
    }

    public ResponseCookie generateRefreshJwtCookie(String refreshToken) {
        return generateCookie(jwtRefreshCookie, refreshToken, "/api/auth/refreshtoken");
    }

    public String getJwtFromCookies(HttpServletRequest request) {
       return getCookieValueByName(request, jwtCookie);
    }

    public  String getJwtRefreshFromCookies(HttpServletRequest request) {
        return getCookieValueByName(request, jwtRefreshCookie);
    }

    public ResponseCookie getCleanJwtCookie(){
        return ResponseCookie.from(jwtCookie, null).path("/api").build();
    }

    public ResponseCookie getCleanJwtRefreshCookie(){
        return ResponseCookie.from(jwtRefreshCookie, null).path("/api/auth/refreshtoken").build();
    }

    // jwtSecret must be 32 bytes long and encoded in BASE64
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromJwtToken(String token) {
        JwtParser parser = Jwts.parser().setSigningKey(key()).build();
        return parser.parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String generateTokenFromUsername(String username){
        return Jwts.builder()
                .issuer("Orion")
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private ResponseCookie generateCookie(String cookieName, String cookieValue, String path) {
        return ResponseCookie
                .from(cookieName, cookieValue)
                .path(path)
                .maxAge(24*60*60) // 24H
                .httpOnly(true)
                .build();
    }

    private String getCookieValueByName(HttpServletRequest request, String cookieName) {
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        if (cookie != null) {
            return cookie.getValue();
        }else {
            return null;
        }
    }
}
