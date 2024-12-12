package com.example.bandera.Services;

import com.example.bandera.Repositories.VehicleRepository;

public class CustomerMapper {

    private final VehicleRepository vehicleRepository;

    public CustomerMapper(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

}
