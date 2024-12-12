package com.example.bandera.RequestModels;

import com.example.bandera.Configuration.TicketStatus;
import com.example.bandera.Entities.ServiceEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDTO {
    /* ------------------------ the start of the columns for the ticket_entity --------------------- */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm a")
    private LocalDateTime dateTime;
    @NotNull
    private List<ServiceEntity> services = new ArrayList<>();

    private double preliminaryCost;

    private double tax;
    @NotNull
    private double subtotal;

    private TicketStatus ticketStatus;
    private CustomerDTO customer;
    private VehicleDTO vehicle;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }

    public void addService(ServiceEntity service) {
        if(!services.contains(service)) {
            services.add(service);
        }
    }

    public double getPreliminaryCost() {
        return preliminaryCost;
    }

    public void setPreliminaryCost(double preliminaryCost) {
        this.preliminaryCost = preliminaryCost;
    }

    public double getTax() {
        return tax;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
}
