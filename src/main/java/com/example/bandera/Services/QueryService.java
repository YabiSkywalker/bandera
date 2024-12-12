package com.example.bandera.Services;


import com.example.bandera.Entities.TicketEntity;
import com.example.bandera.Entities.VehicleEntity;
import com.example.bandera.Repositories.TicketRepository;
import com.example.bandera.Repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {
    private final TicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;

    public QueryService(TicketRepository ticketRepository, VehicleRepository vehicleRepository) {
        this.ticketRepository = ticketRepository;
        this.vehicleRepository = vehicleRepository;
    }
    public List<TicketEntity> findTicketsByAssignee(String employeeId) {
        return ticketRepository.findByAssignee(employeeId);
    }

    public List<VehicleEntity> findCustomerVehicles(String customerId) {
        return vehicleRepository.findByOwner(customerId);
    }
    public List<TicketEntity> findTicketsByVin(String vin) {
        VehicleEntity vehicle = vehicleRepository.findByVin(vin);
        return ticketRepository.findByVehicle(vehicle.getVehicleId());
    }

}
