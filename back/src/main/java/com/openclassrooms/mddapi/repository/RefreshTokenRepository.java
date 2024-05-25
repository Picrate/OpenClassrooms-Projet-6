package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.model.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(SimpleUserDto user);
    Optional<RefreshToken> findByUser(SimpleUserDto user);
    boolean existsByToken(String token);
    boolean existsByUser(SimpleUserDto user);
}
