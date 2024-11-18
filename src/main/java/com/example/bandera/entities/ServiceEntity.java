package com.example.bandera.entities;

import java.util.List;

public class ServiceEntity {
    private String serviceType;
    private String serviceDetails;
    private List<PartEntity> partsRequired;

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

    public List<PartEntity> getPartsRequired() {
        return partsRequired;
    }

    public void setPartsRequired(List<PartEntity> partsRequired) {
        this.partsRequired = partsRequired;
    }
}
