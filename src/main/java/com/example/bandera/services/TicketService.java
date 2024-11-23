package com.example.bandera.services;

import com.example.bandera.entities.CustomersEntity;
import com.example.bandera.entities.TicketEntity;
import com.example.bandera.entities.VehicleEntity;
import com.example.bandera.repositories.CustomerRepository;
import com.example.bandera.repositories.TicketRepository;
import com.example.bandera.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketService {
    //private final CustomerService customerService;
    @Autowired
    private VehicleRepository vehicleRepository;

    //Do I still neeed this ?
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TicketRepository ticketRepository;
    //@Autowired
    /*
    public TicketService(TicketRepository ticketRepo, CustomerService customerService) {
        this.ticketRepository = ticketRepo;
        this.customerService = customerService;
    }
    */
    public TicketEntity addTicket(TicketEntity ticket) {
        CustomersEntity customer = ticket.getCustomer();
        if (customer.getVehicle() != null && customer.getVehicle().getVehicleId() == null) {
            VehicleEntity saveVehicle = vehicleRepository.save(customer.getVehicle());
            customer.setVehicle(saveVehicle);
        }

        if (customer.getId() == null) {
            CustomersEntity savedCustomer = customerRepository.save(customer);
            ticket.setCustomer(savedCustomer); // Update the ticket with the saved customer
        } else {
            // Ensure the customer is fetched from the database if it already exists
            customer = customerRepository.findById(customer.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
            ticket.setCustomer(customer);
        }



        TicketEntity savedTicket = ticketRepository.save(ticket);
        //System.out.println(savedTicket);
        return savedTicket;
    }

    public List<TicketEntity> getAllTickets() {
        return ticketRepository.findAll();
    }

    public TicketEntity updateTicket(String id, TicketEntity update) {
        //Getting the customer entry first
        TicketEntity t = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        t.setDateTime(update.getDateTime());
        t.setServiceEntity(update.getServiceEntity());
        t.setLaborHours(update.getLaborHours());
        t.setLaborRate(update.getLaborRate());
        t.setPreliminaryCost(update.getPreliminaryCost());
        t.setTicketStatus(update.getTicketStatus());
        //t.setCustomer(update.getCustomer());


        return ticketRepository.save(t);
    }

}
