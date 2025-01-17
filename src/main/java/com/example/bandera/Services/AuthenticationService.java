package com.example.bandera.Services;


import com.example.bandera.Repositories.AuthorizationRepository;
import com.example.bandera.Repositories.EmployeeRepository;
import com.example.bandera.DataModels.EmployeeRegistryDTO;
import com.example.bandera.Secrets.JwtTokenUtil;
import com.example.bandera.Secrets.PasswordUtil;
import com.example.bandera.entities.AuthorizationEntity;
import com.example.bandera.entities.EmployeesEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticationService {
    private final AuthorizationRepository authorizationRepository;
    private final EmployeeRepository employeeRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationService(AuthorizationRepository authorizationRepository, EmployeeRepository employeeRepository, JwtTokenUtil jwtTokenUtil) {
        this.authorizationRepository = authorizationRepository;
        this.employeeRepository = employeeRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String authenticate(String username, String password) {
        AuthorizationEntity authorizationEntity = authorizationRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!PasswordUtil.verifyPassword(password, authorizationEntity.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Generate JWT token
        return jwtTokenUtil.generateToken(username);
    }

    public void register(EmployeeRegistryDTO e) {
        if (authorizationRepository.findByEmail(e.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        String userId = UUID.randomUUID().toString();
        String hashPassword = PasswordUtil.hashPassword(e.getPassword());

        AuthorizationEntity newRegistry = new AuthorizationEntity();
        newRegistry.setId(userId); // shared id with the authorization
        newRegistry.setEmail(e.getEmail());
        newRegistry.setPassword(hashPassword);

        /* ---------Account related info --------- */
        EmployeesEntity newEmployee = new EmployeesEntity();
        newEmployee.setId(userId); // shared id with the authorization
        newEmployee.setFirstName(e.getFirstName());
        newEmployee.setLastName(e.getLastName());
        newEmployee.setPhoneNumber(e.getPhoneNumber());
        newEmployee.setAddress(e.getAddress());
        newEmployee.setEmail(e.getEmail());

        authorizationRepository.save(newRegistry);
        employeeRepository.save(newEmployee);

    }
}

