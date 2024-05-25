package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.RefreshToken;
import com.openclassrooms.mddapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
}
