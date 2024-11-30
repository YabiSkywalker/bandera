package com.example.bandera.controllers;

import com.example.bandera.entities.EmployeesEntity;
import com.example.bandera.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeesController {

    /* ----------------------------------------------- CRUD FORM ----------------------------------------------------------- */
    @Autowired
    private EmployeeService employeeService;
    /* ------------------------- CREATE ---------------------------- */

    @Operation(summary = "Add new employee as a user")
    @PostMapping("/addNewEmployee")

    public ResponseEntity<?> addNewEmployee(@RequestBody EmployeesEntity employeeInfo) {
        EmployeesEntity employees = employeeService.addEmployee(employeeInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(employees);
    }

    @Operation(summary = "Get all existing employees")
    @GetMapping("/getAllEmployees")
    public List<EmployeesEntity> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /* ------------------------- READ ---------------------------- */

    @Operation(summary = "Get employee by employee id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeByIdById(@PathVariable String id) {

        try {
            EmployeesEntity employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Update employee account information by their employee id.")
    @PatchMapping("/{id}/update")
    public ResponseEntity<EmployeesEntity> updateEmployeeInfo(@PathVariable String id, @RequestBody EmployeesEntity update) {
        EmployeesEntity updateEmployee = employeeService.updateEmployeeInfo(id, update);
        return ResponseEntity.ok(updateEmployee);
    }

    @Operation(summary = "Delete employee account by their employee id")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<EmployeesEntity> deleteEmployeeById(@PathVariable String id) {
        EmployeesEntity deletedEmployee = employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok(deletedEmployee);
    }


}
