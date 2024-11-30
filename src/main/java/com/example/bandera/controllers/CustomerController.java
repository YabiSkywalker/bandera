package com.example.bandera.controllers;

import com.example.bandera.entities.CustomersEntity;
import com.example.bandera.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    /* ----------------------------------------------- CRUD FORM ----------------------------------------------------------- */

    @Autowired
    private CustomerService customerService;


    /* ------------------------- CREATE ---------------------------- */
    @Operation(summary = "Add a new customer")
    @PostMapping("/addNewCustomer")
    public ResponseEntity<CustomersEntity> addCustomer(@Valid @RequestBody CustomersEntity customer) {


        CustomersEntity c = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

            /* ------------------------- READ ---------------------------- */
    @Operation(summary = "Find customer by their customer id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {

        try {
            CustomersEntity customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all existing customers")
    @GetMapping("/getAllCustomers")
    public List<CustomersEntity> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /* ------------------------- UPDATE ---------------------------- */

    @Operation(summary = "Update customer information")
    @PatchMapping("/{id}/update")
    public ResponseEntity<CustomersEntity> updateCustomerInfo(@PathVariable String id, @RequestBody CustomersEntity update) {
        CustomersEntity updateCustomer = customerService.updateCustomerInfo(id, update);
        return ResponseEntity.ok(updateCustomer);
    }


    /* ------------------------- DELETE ---------------------------- */
    //@DeleteMapping("/{id}/delete")
    public ResponseEntity<CustomersEntity> deleteCustomerById(@PathVariable String id) {
        CustomersEntity deletedCustomer = customerService.deleteCustomerById(id);
        return ResponseEntity.ok(deletedCustomer);
    }
}
