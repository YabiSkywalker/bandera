package com.example.bandera.Repositories;

import com.example.bandera.entities.EmployeesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeesEntity, String> {
    @Query("{ 'isDefault': true }")
    Optional<EmployeesEntity> findDefaultEmployee();
    Optional<EmployeesEntity> findById(String id);


}