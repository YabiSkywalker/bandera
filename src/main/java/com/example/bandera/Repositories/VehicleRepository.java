package com.example.bandera.Repositories;

import com.example.bandera.Entities.VehicleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleRepository extends MongoRepository<VehicleEntity, String> {
    VehicleEntity findByVin(String vehicleVin);
    List<VehicleEntity> findByOwner(String customerId);
}
