package com.example.bandera.Services;

import com.example.bandera.Configuration.TicketStatus;
import com.example.bandera.DataModels.ResponseModels.SetStatusResponseDTO;
import com.example.bandera.Repositories.CustomerRepository;
import com.example.bandera.Repositories.EmployeeRepository;
import com.example.bandera.Repositories.TicketRepository;
import com.example.bandera.Repositories.VehicleRepository;
import com.example.bandera.DataModels.TicketDTO;
import com.example.bandera.TicketStatusEvent;
import com.example.bandera.entities.*;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


@Service
public class TicketService {
    //private final CustomerService customerService;


    private final ApplicationEventPublisher eventPublisher;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    //find tickets with the id of the customer.
    @Autowired
    public TicketService(ApplicationEventPublisher eventPublisher, TicketRepository ticketRepository) {
        this.eventPublisher = eventPublisher;
        this.ticketRepository = ticketRepository;
    }

    public TicketService(ApplicationEventPublisher eventPublisher) {

        this.eventPublisher = eventPublisher;
    }

    public List<TicketEntity> getTicketsByCustomerId(String customerId) {
        return ticketRepository.findByCustomerId(customerId);
    }

    public TicketEntity addTicket(String employeeId, TicketDTO ticketDto) {
        Mapper mapNewTicket = new Mapper();
        TicketEntity newTicket = new TicketEntity();

        newTicket.setDateTime(ticketDto.getDateTime());
        //check to see the employee exists
        EmployeesEntity assignee;
        if (employeeId != null) {
            // Fetch the employee from the database to ensure the ID is valid
            assignee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
        } else {
            // Assign to a default employee (e.g., "system admin")
            assignee = employeeRepository.findDefaultEmployee()
                    .orElseThrow(() -> new IllegalStateException("Default employee not configured"));
        }
        //set the assignee to the new ticket
        newTicket.setAssignee(assignee);
        newTicket.setServiceEntity(ticketDto.getServices());
        newTicket.setPreliminaryCost(ticketDto.getPreliminaryCost());
        newTicket.setTax(ticketDto.getTax());
        newTicket.setSubtotal(ticketDto.getSubtotal());
        newTicket.setTicketStatus(ticketDto.getTicketStatus());

        //GET the customer email, and check the customer repo if the customer exists
        CustomersEntity existingCustomer = customerRepository.findByEmail(ticketDto.getCustomer().getEmail());
        if(existingCustomer == null) {
            //if the customer does not exist, then save right here
            CustomersEntity newCustomer = customerRepository.save(mapNewTicket.mapCustomer(ticketDto));
            //after saving, set it to the new ticket
            newTicket.setCustomer(newCustomer);
        } else {
            //if the customer does in fact exist, then simply set that customer to the newTicket
            newTicket.setCustomer(existingCustomer);
        }

        //
        // so far the CustomerEntity is saved to the new TicketEntity
        //

        VehicleEntity existingVehicle = vehicleRepository.findByVin(ticketDto.getVehicle().getVin());
        if(existingVehicle == null) {
            VehicleEntity newVehicle = vehicleRepository.save(mapNewTicket.mapVehicle(newTicket.getCustomer(), ticketDto.getVehicle()));
            newTicket.setVehicle(newVehicle);
        } else {
            newTicket.setVehicle(existingVehicle);
        }

        //
        // so far the CustomerEntity is saved, the VehicleEntity is saved with the owner set to the CustomerEntity
        //

        return ticketRepository.save(newTicket); //ticketRepository.save(mapNewTicket.mapTicket(assignee, newVehicle, newCustomer, ticketDto));
    }

    public List<TicketEntity> getAllTickets() {
        return ticketRepository.findAll();
    }
    public TicketEntity findTicketbyId(String ticketid) {
        return ticketRepository.findById(ticketid)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found."));
    }

    public TicketEntity addServices(String id, ServiceEntity services) {
        //Getting the ticket entry first
        TicketEntity t = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        t.addService(services);

        return ticketRepository.save(t);
    }

    //public

    public TicketEntity removeServices(String id, int index) {
        TicketEntity t = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        t.removeServiceByIndex(index);
        return ticketRepository.save(t);
    }


    public String deleteTicket(String ticketId) {
        TicketEntity t = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticketRepository.delete(t);

        return ticketId + "successfully deleted";
    }

    //only updates the assignee portion of the JSON
    public TicketEntity updateAssignee(String ticketId, String employeeId) {
        //identify the ticket first with this
        TicketEntity t = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        EmployeesEntity e = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        t.setAssignee(e);
        ticketRepository.save(t);


        employeeRepository.save(e);

        return t;
    }
    public TicketEntity updateCosts(String id, double tax, double preliminaryCost, double subtotal) {
        //Getting the ticket entry first
        TicketEntity t = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        t.setTax(tax);
        t.setPreliminaryCost(preliminaryCost);
        t.setSubtotal(subtotal);

        return ticketRepository.save(t);

    }
    public TicketEntity setAppointment(String id, String dateTime) {
        //Getting the ticket entry first
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        // Parse the date string into a LocalDateTime
        LocalDateTime parsedDateTime;
        try {
            parsedDateTime = LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd hh:mm a'.");
        }

        // Fetch and update the ticket
        TicketEntity t = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        t.setDateTime(parsedDateTime);
        return ticketRepository.save(t);
    }




    public SetStatusResponseDTO setStatus(String id, TicketStatus status) {
        //Getting the ticket entry first
        TicketEntity t = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));


        TicketStatus oldStatus = t.getTicketStatus();
        if (!oldStatus.equals(status)) {
            t.setTicketStatus(status);
            ticketRepository.save(t);

            TicketStatusEvent statusChange = new TicketStatusEvent(this, id, oldStatus, status);
            eventPublisher.publishEvent(statusChange);
        }

        SetStatusResponseDTO newStatus = new SetStatusResponseDTO();
        newStatus.setId(t.getId());
        newStatus.setTicketStatus(t.getTicketStatus());

        return newStatus;
    }






}
