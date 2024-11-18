package com.example.bandera.controllers;

import com.example.bandera.dataTransferObjects.CustomerUpdateDTO;
import com.example.bandera.services.CustomerService;
import com.example.bandera.entities.CustomersEntity;
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
    //@PostMapping("/addNewCustomer")
    public ResponseEntity<CustomerUpdateDTO> addNewCustomer(@Valid @RequestBody CustomerUpdateDTO customerInfo) {
        CustomerUpdateDTO customers = customerService.addCustomer(customerInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(customers);
    }

    /* ------------------------- READ ---------------------------- */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {

        try {
            CustomersEntity customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAllCustomers")
    public List<CustomersEntity> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /* ------------------------- UPDATE ---------------------------- */

    //@PatchMapping("/{id}/update")
    public ResponseEntity<CustomersEntity> updateCustomerInfo(@PathVariable String id, @RequestBody CustomerUpdateDTO update) {
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
