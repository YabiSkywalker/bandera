package com.example.bandera.dataTransferObjects;

import com.example.bandera.TicketStatus;
import com.example.bandera.entities.ServiceEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDTO {
    /* ------------------------ the start of the columns for the ticket_entity --------------------- */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm a")
    private LocalDateTime dateTime;
    @NotNull
    private List<ServiceEntity> services = new ArrayList<>();

    private BigDecimal preliminaryCost;

    private BigDecimal tax;
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

    public BigDecimal getPreliminaryCost() {
        return preliminaryCost;
    }

    public void setPreliminaryCost(BigDecimal preliminaryCost) {
        this.preliminaryCost = preliminaryCost;
    }

    public BigDecimal getTax() {
        return tax;
    }
    public void setTax(BigDecimal tax) {
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
