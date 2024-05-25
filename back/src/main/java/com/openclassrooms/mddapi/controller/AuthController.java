package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.RefreshTokenDto;
import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.exceptions.TokenRefreshException;
import com.openclassrooms.mddapi.model.ERole;
import com.openclassrooms.mddapi.model.RefreshToken;
import com.openclassrooms.mddapi.model.Role;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import com.openclassrooms.mddapi.payload.request.TokenRefreshRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.payload.response.UserInfoResponse;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.security.services.RefreshTokenService;
import com.openclassrooms.mddapi.security.services.UserDetailsImpl;
import com.openclassrooms.mddapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "http://localhost:4002", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    AuthController(PasswordEncoder passwordEncoder,
                   JwtUtils jwtUtils,
                   UserService userService,
                   RefreshTokenService refreshTokenService) {
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword())
        );

        if(authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            UserInfoResponse userResponse = new UserInfoResponse(
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles
            );

            RefreshTokenDto refreshToken = refreshTokenService.generateRefreshTokenDto(userDetails.getId());

            /*
            ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                    .body(new UserInfoResponse(
                            userDetails.getId(),
                            userDetails.getUsername(),
                            userDetails.getEmail(),
                            roles
                    ));
            */
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(new JwtResponse(refreshToken.getToken(),userResponse));

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        // Assign Role user to new user
        Set<Role> roles = new HashSet<>();
        Role userRole = userService.getRole(ERole.ROLE_USER).orElseThrow(
                () -> new RuntimeException("Error: Role not found!")
        );
        roles.add(userRole);
        user.setRoles(roles);

        // save new user
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/logout")
    public ResponseEntity<MessageResponse> logout() {

        String currentPrincipalName = getCurrentPrincipalName(SecurityContextHolder.getContext());
        SimpleUserDto currentUser = this.userService.getSimpleUserDtoByEmailOrUsername(currentPrincipalName);
        ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
        this.refreshTokenService.deleteByUserId(currentUser.getId());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new MessageResponse("Déconnexion réussie"));

        /*
        if(!Objects.equals(principal.toString(), "anonymousUser")) {
            String userId = ((UserDetailsImpl)principal).getId();
            this.refreshTokenService.deleteByUserId(userId);
        }
        ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new MessageResponse("Déconnexion réussie"));
         */
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<JwtResponse> refreshtoken(@RequestBody @Valid TokenRefreshRequest refreshRequest, HttpServletRequest request) {

        if((refreshRequest.getRefreshToken() != null)&&(!refreshRequest.getRefreshToken().isEmpty())){

            return refreshTokenService.findByToken(refreshRequest.getRefreshToken())
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);
                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .body(new JwtResponse(refreshRequest.getRefreshToken(), new UserInfoResponse(
                                        user.getId(),
                                        user.getUsername(),
                                        user.getEmail(),
                                        this.userService.getUserRoles(user.getUsername())
                                )));
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshRequest.getRefreshToken(),
                            "Je jeton n'est pas présent en base !"));
        }

        return ResponseEntity.badRequest().build();
        /*
        if((refreshToken != null)&&(!refreshToken.isEmpty())){
            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);
                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .body(new MessageResponse("Jeton rafraichi avec succès!"));
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken,
                            "Je jeton n'est pas présent en base !"));
        }
        */
    }

    private String getCurrentPrincipalName(SecurityContext securityContext){
        Authentication authentication = securityContext.getAuthentication();
        if(authentication.isAuthenticated())
            return authentication.getName();
        else
            return null;
    }


}
