package com.example.bandera.Entities;

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
    public static class PartEntity {
        private String partName;
        private int partQty;
        private double partPrice;

        public String getPartName() {
            return partName;
        }

        public void setPartName(String partName) {
            this.partName = partName;
        }

        public int getPartQty() {
            return partQty;
        }

        public void setPartQty(int partQty) {
            this.partQty = partQty;
        }

        public double getPartPrice() {
            return partPrice;
        }

        public void setPartPrice(double partPrice) {
            this.partPrice = partPrice;
        }
    }
}
