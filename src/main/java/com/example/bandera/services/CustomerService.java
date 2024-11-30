package com.example.bandera.services;

import com.example.bandera.entities.CustomersEntity;
import com.example.bandera.repositories.CustomerRepository;
import com.example.bandera.repositories.VehicleRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CustomerRepository customerRepository;

    //@Autowired
   // public CustomerService(CustomerRepository customerRepo) {
    ///    this.customerRepository = customerRepo;
   // }

    public CustomerService() {

    }

    public CustomersEntity getCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found."));
    }

    public List<CustomersEntity> getAllCustomers() {
        List<CustomersEntity> customers = customerRepository.findAll();

        return customers;

    }

    public CustomersEntity addCustomer(CustomersEntity customer) {



        CustomersEntity savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }




    public CustomersEntity updateCustomerInfo(String id, CustomersEntity update) {
        //Getting the customer entry first
        CustomersEntity c = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // leveraging a new "Entity" but really its called a Data Transfer Object (DTO) - just a way to be able to take in parameters in the form of a @RequestBody within the Controller
            c.setFirstName(update.getFirstName());
            c.setLastName(update.getLastName());
            c.setPhoneNumber(update.getPhoneNumber());
            c.setEmail(update.getEmail());

        return customerRepository.save(c);
     }


     public CustomersEntity deleteCustomerById(String id) {
        //Getting the customer you wanna delete first here
         CustomersEntity customer = customerRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Customer not found."));

        //actually performing the delete here
        customerRepository.deleteById(id);

        //return the customer that just got deleted (just in case I forget why I wrote this)
        return customer;
    }

}
