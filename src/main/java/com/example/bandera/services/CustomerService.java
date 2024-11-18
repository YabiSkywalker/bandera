package com.example.bandera.services;

import com.example.bandera.dataTransferObjects.CustomerUpdateDTO;
import com.example.bandera.dataTransferObjects.VehicleUpdateDTO;
import com.example.bandera.entities.CustomersEntity;
import com.example.bandera.entities.VehicleEntity;
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

    @Autowired
    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepository = customerRepo;
    }

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

    public CustomerUpdateDTO addCustomer(CustomerUpdateDTO customer) {
        // Create and map customer entity from DTO
        CustomersEntity c = new CustomersEntity();
        c.setFirstName(customer.getFirstName());
        c.setLastName(customer.getLastName());
        c.setPhoneNumber(customer.getPhoneNumber());
        c.setEmail(customer.getEmail());

        // Save the customer first so it gets an ID
        CustomersEntity savedCustomer = customerRepository.save(c);

        // Create and map vehicle entity from DTO
        VehicleEntity v = new VehicleEntity();
        v.setYear(customer.getVehicle().getYear());
        v.setMake(customer.getVehicle().getMake());
        v.setModel(customer.getVehicle().getModel());
        v.setVin(customer.getVehicle().getVin());

        // Set the customer reference in the vehicle
        //v.setCustomer(savedCustomer);

        // Save the vehicle
        VehicleEntity savedVehicle = vehicleRepository.save(v);

        // Set the vehicle in the saved customer entity
        savedCustomer.setVehicle(savedVehicle);

        // Save the updated customer entity (with vehicle reference)
        customerRepository.save(savedCustomer);

        // Prepare DTO for response
        CustomerUpdateDTO savedCustomerDTO = new CustomerUpdateDTO();
        savedCustomerDTO.setId(savedCustomer.getId());
        savedCustomerDTO.setFirstName(savedCustomer.getFirstName());
        savedCustomerDTO.setLastName(savedCustomer.getLastName());
        savedCustomerDTO.setPhoneNumber(savedCustomer.getPhoneNumber());
        savedCustomerDTO.setEmail(savedCustomer.getEmail());

        VehicleUpdateDTO vehicleUpdateDTO = new VehicleUpdateDTO();
        vehicleUpdateDTO.setMake(savedVehicle.getMake());
        vehicleUpdateDTO.setModel(savedVehicle.getModel());
        vehicleUpdateDTO.setYear(savedVehicle.getYear());
        vehicleUpdateDTO.setVin(savedVehicle.getVin());

        savedCustomerDTO.setVehicle(vehicleUpdateDTO);

        return savedCustomerDTO;
    }


    public CustomersEntity updateCustomerInfo(String id, CustomerUpdateDTO update) {
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
