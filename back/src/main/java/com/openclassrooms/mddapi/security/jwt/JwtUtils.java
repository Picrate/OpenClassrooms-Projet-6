package com.openclassrooms.mddapi.security.jwt;

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

    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getEmail());
        return ResponseCookie
                .from(jwtCookie, jwt)
                .path("/api").maxAge(24*60*60)
                .httpOnly(true)
                .build();
    }

    public ResponseCookie getCleanJwtCookie(){
        return ResponseCookie.from(jwtCookie, null).path("/api").build();
    }

    // jwtSecret must be 32 bytes long and encoded in BASE64
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
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

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        }  else {
            return null;
        }
    }
}
