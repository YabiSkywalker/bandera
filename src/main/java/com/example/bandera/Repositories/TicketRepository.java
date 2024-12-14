package com.example.bandera.Repositories;

import com.example.bandera.entities.TicketEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<TicketEntity, String> {
    List<TicketEntity> findByAssignee(String employeeId);

    @Query("{ 'customer.id': ?0 }")
    List<TicketEntity> findByCustomerId(String customerId);

    List<TicketEntity> findByVehicle(String vin);
}