package com.example.bandera.DataModels;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerDTO {

    @NotNull
    @Size(min = 1, max = 15)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 15)
    private String lastName;
    @NotNull
    @Size(min = 1, max = 15)
    private String phoneNumber;
    @NotNull
    @Size(min = 1, max = 30)
    private String email;

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
}
