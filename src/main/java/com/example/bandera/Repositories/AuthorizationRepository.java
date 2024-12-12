package com.example.bandera.Repositories;


import com.example.bandera.Entities.AuthorizationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationRepository extends MongoRepository<AuthorizationEntity, String> {

    Optional<AuthorizationEntity> findByEmail(String email);
}
