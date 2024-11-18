package com.example.bandera.repositories;

import com.example.bandera.entities.TicketEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends MongoRepository<TicketEntity, String> {

}
