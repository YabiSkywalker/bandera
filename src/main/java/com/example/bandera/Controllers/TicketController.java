package com.example.bandera.Controllers;

import com.example.bandera.DataModels.TicketDTO;
import com.example.bandera.Configuration.TicketStatus;
import com.example.bandera.entities.ServiceEntity;
import com.example.bandera.entities.TicketEntity;
import com.example.bandera.Repositories.TicketRepository;
import com.example.bandera.Services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository ticketRepository;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public TicketController() {

    }

    @Operation(summary = "Create a new ticket")
    @PostMapping("/createTicket")
    public ResponseEntity<TicketEntity> createNewTicket(@RequestParam(required = false) String employeeId, @Valid @RequestBody TicketDTO ticketDto) {
        System.out.println("Received payload: " + ticketDto);
        TicketEntity t = ticketService.addTicket(employeeId,ticketDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

    @Operation(summary = "Get all existing tickets")
    @GetMapping("/getAllTickets")
    public ResponseEntity<List<TicketEntity>> getAllTickets() {
        List<TicketEntity> tickets = ticketService.getAllTickets();
        System.out.println("Fetched Tickets: " + tickets);
        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Find ticket by ticket id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketByIdById(@PathVariable String id) {

        try {
            TicketEntity ticket = ticketService.findTicketbyId(id);
            return ResponseEntity.ok(ticket);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // -------------------- PATCH MAPPINGS --------------------/

    @Operation(summary = "Add a service to a ticket by the ticket id")
    @PatchMapping("/{id}/addServices")
    public ResponseEntity<TicketEntity> addServices(@PathVariable String id, @RequestBody ServiceEntity services) {
        TicketEntity updateTicket = ticketService.addServices(id, services);
        //implement logic to send automated text message/email
        return ResponseEntity.ok(updateTicket);
    }

    @Operation(summary = "Remove a service to a ticket by the ticket id")
    @DeleteMapping("/{id}/removeServices")
    public ResponseEntity<TicketEntity> removeServices(@PathVariable String id, @RequestParam int index) {
        return ResponseEntity.ok(ticketService.removeServices(id, index));
    }

    @Operation(summary = "Locate ticket by ticket id, and locate employee by employee id then update ticket assignee")
    @PatchMapping("/updateAssignee/{ticketId}")
    public ResponseEntity<TicketEntity> updateAssignee(@PathVariable String ticketId, String employeeId) {
        TicketEntity updatedTicket = ticketService.updateAssignee(ticketId, employeeId);
        return ResponseEntity.ok(updatedTicket);
    }


    @PatchMapping("/{id}/updateCosts")
    public ResponseEntity<TicketEntity> updateCosts(String id, double tax, double preliminaryCost, double subtotal) {
        TicketEntity updateCosts = ticketService.updateCosts(id, tax, preliminaryCost, subtotal);
        return ResponseEntity.ok(updateCosts);
    }


    @Operation(summary = "Update ticket appointment date by ticket id")
    @PatchMapping("/{id}/setAppointment")
    public ResponseEntity<TicketEntity> setAppointment(String id, @RequestParam String dateTime) {
        TicketEntity setAppointment = ticketService.setAppointment(id, dateTime);
        return ResponseEntity.ok(setAppointment);
    }

    @Operation(summary = "Update ticket status by ticket id")
    @PatchMapping("/{id}/setStatus")
    public ResponseEntity<TicketEntity> setStatus(String id, TicketStatus status) {
        TicketEntity setStatus = ticketService.setStatus(id, status);
        return ResponseEntity.ok(setStatus);
    }
}
