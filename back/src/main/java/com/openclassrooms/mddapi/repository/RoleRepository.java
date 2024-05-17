package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.ERole;
import com.openclassrooms.mddapi.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
