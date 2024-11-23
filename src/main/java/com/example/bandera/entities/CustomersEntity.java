package com.example.bandera.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers_entity")
public class CustomersEntity {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @DBRef
    private VehicleEntity vehicle;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }
}
