package com.example.bandera.repositories;

import com.example.bandera.entities.VehicleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VehicleRepository extends MongoRepository<VehicleEntity, String> {
    Optional<VehicleEntity> findByVin(String vehicleVin);
}
