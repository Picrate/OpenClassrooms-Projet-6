package com.openclassrooms.mddapi.security.services;

import com.openclassrooms.mddapi.dto.RefreshTokenDto;
import com.openclassrooms.mddapi.exceptions.TokenRefreshException;
import com.openclassrooms.mddapi.mapper.RefreshTokenMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.RefreshToken;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.RefreshTokenRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${oc.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserMapper userMapper;
    private final RefreshTokenMapper refreshTokenMapper;

    public RefreshTokenService(
            UserRepository userRepository,
            RefreshTokenRepository refreshTokenRepository,
            UserMapper userMapper,
            RefreshTokenMapper refreshTokenMapper
    ) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userMapper = userMapper;
        this.refreshTokenMapper = refreshTokenMapper;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String userId){
        RefreshToken refreshToken = new RefreshToken();
        if(this.userRepository.findById(userId).isPresent()) {
            User user = this.userRepository.findById(userId).get();
            refreshToken.setUser(this.userMapper.userToSimpleDto(user));
            refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshTokenRepository.save(refreshToken);
        }
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if(token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Le jeton est expiré. Vous devez vous authentifer de nouveaux");
        }
        return token;
    }

    public void deleteByUserId(String userId) {
        if(this.userRepository.findById(userId).isPresent())
            refreshTokenRepository.deleteByUser(this.userRepository.findById(userId).get());
    }

    public RefreshTokenDto generateRefreshTokenDto(String userId){
        return refreshTokenMapper.toRefreshTokenDto(createRefreshToken(userId));
    }


}
