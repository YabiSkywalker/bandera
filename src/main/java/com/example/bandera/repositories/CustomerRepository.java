package com.example.bandera.repositories;

import com.example.bandera.entities.CustomersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<CustomersEntity, String> {
    CustomersEntity findByEmail(String email);
    Optional<CustomersEntity> findById(String id);
}

