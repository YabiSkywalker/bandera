package com.example.bandera.services;

import com.example.bandera.entities.TicketEntity;
import com.example.bandera.repositories.CustomerRepository;
import com.example.bandera.repositories.TicketRepository;
import com.example.bandera.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketService {
    private final CustomerService customerService;
    @Autowired
    private VehicleRepository vehicleRepository;

    //Do I still neeed this ?
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TicketRepository ticketRepository;
    //@Autowired
    public TicketService(TicketRepository ticketRepo, CustomerService customerService) {
        this.ticketRepository = ticketRepo;
        this.customerService = customerService;
    }
    public TicketEntity addTicket(TicketEntity ticket) {
       // CustomersEntity existingCustomer = customerRepository.findByEmail(ticket.getCustomer().getEmail());

        TicketEntity savedTicket = ticketRepository.save(ticket);


        //System.out.println(savedTicket);
        return savedTicket;
    }

    public List<TicketEntity> getAllTickets() {
        List<TicketEntity> tickets = ticketRepository.findAll();
        return tickets;
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
