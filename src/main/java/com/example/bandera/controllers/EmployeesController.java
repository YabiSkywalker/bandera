package com.example.bandera.controllers;

import com.example.bandera.entities.EmployeesEntity;
import com.example.bandera.services.EmployeeService;
import com.example.bandera.dataTransferObjects.EmployeeUpdateDTO;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
@RestController
@RequestMapping("/employees")

 */
public class EmployeesController {

    /* ----------------------------------------------- CRUD FORM ----------------------------------------------------------- */

    @Autowired
    private EmployeeService employeeService;
    /* ------------------------- CREATE ---------------------------- */

    @PostMapping("/addNewEmployee")

    public ResponseEntity<?> addNewEmployee(@RequestBody EmployeesEntity employeeInfo) {
        EmployeesEntity employees = employeeService.addEmployee(employeeInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(employees);
    }

    /* ------------------------- READ ---------------------------- */

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeByIdById(@PathVariable String id) {

        try {
            EmployeesEntity employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<EmployeesEntity> updateEmployeeInfo(@PathVariable String id, @RequestBody EmployeeUpdateDTO update) {
        EmployeesEntity updateEmployee = employeeService.updateEmployeeInfo(id, update);
        return ResponseEntity.ok(updateEmployee);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<EmployeesEntity> deleteEmployeeById(@PathVariable String id) {
        EmployeesEntity deletedEmployee = employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok(deletedEmployee);
    }


}
