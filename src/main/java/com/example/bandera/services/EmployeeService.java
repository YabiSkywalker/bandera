package com.example.bandera.services;

import com.example.bandera.entities.EmployeesEntity;
import com.example.bandera.repositories.EmployeeRepository;
import com.example.bandera.dataTransferObjects.EmployeeUpdateDTO;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepository = employeeRepo;
    }
    public List<EmployeesEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeesEntity getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found."));
    }

    public EmployeesEntity addEmployee(EmployeesEntity employee) {
        return employeeRepository.save(employee);
    }

    public EmployeesEntity updateEmployeeInfo(String id, EmployeeUpdateDTO update) {
        EmployeesEntity e = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (EmployeeUpdateDTO.getFirstName() != null) {
            e.setFirstName(update.getFirstName());
        }
        if (EmployeeUpdateDTO.getLastName() != null) {
            e.setLastName(update.getLastName());
        }
        if (EmployeeUpdateDTO.getPhoneNumber() != null) {
            e.setPhoneNumber(update.getPhoneNumber());
        }
        if (EmployeeUpdateDTO.getEmail() != null) {
            e.setEmail(update.getEmail());
        }
        return employeeRepository.save(e);
    }
    public EmployeesEntity deleteEmployeeById(String id) {
        EmployeesEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found."));

        employeeRepository.deleteById(id);

        return employee;
    }


}
