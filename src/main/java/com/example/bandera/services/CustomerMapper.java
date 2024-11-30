package com.example.bandera.services;

import com.example.bandera.repositories.VehicleRepository;

public class CustomerMapper {

    private final VehicleRepository vehicleRepository;

    public CustomerMapper(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

}
