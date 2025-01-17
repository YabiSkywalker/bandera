package com.example.bandera.Services;

import com.example.bandera.DataModels.EmployeeRegistryDTO;
import com.example.bandera.DataModels.TicketDTO;
import com.example.bandera.DataModels.VehicleDTO;
import com.example.bandera.entities.CustomersEntity;
import com.example.bandera.entities.EmployeesEntity;
import com.example.bandera.entities.TicketEntity;
import com.example.bandera.entities.VehicleEntity;
import com.example.bandera.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    @Autowired
    private EmployeeRepository employeeRepository;
    //purely for mapping incoming requests to entities
    public CustomersEntity mapCustomer(TicketDTO ticketDto) {
        CustomersEntity mappedCustomer = new CustomersEntity();
        mappedCustomer.setFirstName(ticketDto.getCustomer().getFirstName());
        mappedCustomer.setLastName(ticketDto.getCustomer().getLastName());
        mappedCustomer.setPhoneNumber(ticketDto.getCustomer().getPhoneNumber());
        mappedCustomer.setEmail(ticketDto.getCustomer().getEmail());
        return mappedCustomer;
    }
    public VehicleEntity mapVehicle(CustomersEntity customer, VehicleDTO vehicleDto) {
        VehicleEntity mappedVehicle = new VehicleEntity();
        mappedVehicle.setYear(vehicleDto.getYear());
        mappedVehicle.setMake(vehicleDto.getMake());
        mappedVehicle.setModel(vehicleDto.getModel());
        mappedVehicle.setVin(vehicleDto.getVin());
        mappedVehicle.setOwner(customer);
        return mappedVehicle;
    }
    public TicketEntity mapTicket(EmployeesEntity assignee, VehicleEntity vehicle, CustomersEntity customer, TicketDTO ticketDto) {
        TicketEntity mappedTicket = new TicketEntity();

        //mapping the ticket details to the ticket
        mappedTicket.setDateTime(ticketDto.getDateTime());
        //mappedTicket.setAssignee(assignee);
        mappedTicket.setServiceEntity(ticketDto.getServices());
        mappedTicket.setPreliminaryCost(ticketDto.getPreliminaryCost());
        mappedTicket.setTax(ticketDto.getTax());
        mappedTicket.setSubtotal(ticketDto.getSubtotal());
        mappedTicket.setTicketStatus(ticketDto.getTicketStatus());
        //mappedTicket.setCustomer(customer);
        //mappedTicket.setVehicle(vehicle);

        System.out.println(mappedTicket);
        return mappedTicket;
    }

    public EmployeeRegistryDTO employeeEntityToDto(EmployeesEntity e) {
        EmployeeRegistryDTO assignee = new EmployeeRegistryDTO();
        assignee.setFirstName(e.getFirstName());
        assignee.setLastName(e.getLastName());
        return assignee;
    }

}
