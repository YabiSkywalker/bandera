package com.example.bandera.Controllers;


import com.example.bandera.Entities.TicketEntity;
import com.example.bandera.Entities.VehicleEntity;
import com.example.bandera.Services.QueryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {
    @Autowired
    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }
    @Operation(summary = "Get all tickets assigned to a specific employee")
    @GetMapping("/assignee/{employeeId}")
    public ResponseEntity<List<TicketEntity>> getTicketsByAssignee(@PathVariable String employeeId) {
        List<TicketEntity> tickets = queryService.findTicketsByAssignee(employeeId);
        if (tickets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Get vehicles owned by a customer")
    @GetMapping("/owner/{customerId}")
    public ResponseEntity<List<VehicleEntity>> findVehiclesByOwner(@PathVariable String customerId) {
        List<VehicleEntity> vehicles = queryService.findCustomerVehicles(customerId);
        return ResponseEntity.ok(vehicles);
    }


    @GetMapping("/service-history/{vin}")
    public ResponseEntity<List<TicketEntity>> findServiceHistory(@PathVariable String vin) {
        List<TicketEntity> tickets = queryService.findTicketsByVin(vin);
        return ResponseEntity.ok(tickets);
    }

}
