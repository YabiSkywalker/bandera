package com.example.bandera.repositories;

import com.example.bandera.entities.EmployeesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<EmployeesEntity, String> {


}