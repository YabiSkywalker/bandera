package com.example.bandera.controllers;

import com.example.bandera.entities.TicketEntity;
import com.example.bandera.repositories.TicketRepository;
import com.example.bandera.services.TicketService;
import jakarta.validation.Valid;
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
    @PostMapping("/createTicket")
    public ResponseEntity<TicketEntity> createNewTicket(@Valid @RequestBody TicketEntity ticketInfo) {

        TicketEntity t = ticketService.addTicket(ticketInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

    @GetMapping("/getAllTickets")
    public ResponseEntity<List<TicketEntity>> getAllTickets() {
        List<TicketEntity> tickets = ticketService.getAllTickets();
        System.out.println("Fetched Tickets: " + tickets);
        return ResponseEntity.ok(tickets);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<TicketEntity> updateTicket(@PathVariable String id, @RequestBody TicketEntity update) {
        TicketEntity updateTicket = ticketService.updateTicket(id, update);
        return ResponseEntity.ok(updateTicket);
    }
}
