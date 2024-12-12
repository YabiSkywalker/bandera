package com.example.bandera.Services;

import com.example.bandera.Entities.EmployeesEntity;
import com.example.bandera.Repositories.AuthorizationRepository;
import com.example.bandera.Repositories.EmployeeRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private AuthorizationRepository authorizationRepository;

    public EmployeeService(EmployeeRepository employeeRepo, AuthorizationRepository authRepo) {
        this.employeeRepository = employeeRepo;
        this.authorizationRepository = authRepo;
    }
    public List<EmployeesEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeesEntity getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found."));
    }

    public EmployeesEntity addEmployee(EmployeesEntity employee) {

        EmployeesEntity savedEmployee = employeeRepository.save(employee);

        return savedEmployee;

    }

    public EmployeesEntity updateEmployeeInfo(String id, EmployeesEntity update) {
        EmployeesEntity e = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        e.setFirstName(update.getFirstName());
        e.setLastName(update.getLastName());
        e.setAddress(update.getAddress());
        e.setPhoneNumber(update.getPhoneNumber());
        e.setEmail(update.getEmail());



        return employeeRepository.save(e);
    }
    public EmployeesEntity deleteEmployeeById(String id) {
        EmployeesEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found."));

        employeeRepository.deleteById(id);

        return employee;
    }


}
