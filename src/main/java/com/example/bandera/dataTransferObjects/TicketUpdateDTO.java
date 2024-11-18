package com.example.bandera.dataTransferObjects;

import com.example.bandera.TicketStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TicketUpdateDTO {
    /*
    @NotNull(message = "Vehicle information is required")
    private VehicleUpdateDTO vehicle;

     */
    @NotNull(message = "Vehicle information is required")
    private CustomerUpdateDTO customer;
    private String serviceType;
    private String serviceDetails;
    private String partsRequired;
    private int partQty;
    private int laborHours;
    private BigDecimal laborRate;
    private BigDecimal preliminaryCost;
    private TicketStatus ticketStatus;
    /* ------------------------ GETTERS && SETTERS --------------------- */

    public CustomerUpdateDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerUpdateDTO customer) {
        this.customer = customer;
    }
    /*
    public VehicleUpdateDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleUpdateDTO vehicle) {
        this.vehicle = vehicle;
    }

     */

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(String serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    public String getPartsRequired() {
        return partsRequired;
    }

    public void setPartsRequired(String partsRequired) {
        this.partsRequired = partsRequired;
    }

    public int getPartQty() {
        return partQty;
    }

    public void setPartQty(int partQty) {
        this.partQty = partQty;
    }

    public int getLaborHours() {
        return laborHours;
    }

    public void setLaborHours(int laborHours) {
        this.laborHours = laborHours;
    }

    public BigDecimal getLaborRate() {
        return laborRate;
    }

    public void setLaborRate(BigDecimal laborRate) {
        this.laborRate = laborRate;
    }

    public BigDecimal getPreliminaryCost() {
        return preliminaryCost;
    }

    public void setPreliminaryCost(BigDecimal preliminaryCost) {
        this.preliminaryCost = preliminaryCost;
    }


}
