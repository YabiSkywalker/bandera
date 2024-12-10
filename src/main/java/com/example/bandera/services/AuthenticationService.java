package com.example.bandera.services;


import com.example.bandera.RequestModels.AuthDTO;
import com.example.bandera.entities.AuthorizationEntity;
import com.example.bandera.repositories.AuthorizationRepository;
import com.example.bandera.repositories.EmployeeRepository;
import com.example.bandera.secrets.JwtTokenUtil;
import com.example.bandera.secrets.PasswordUtil;
import org.springframework.stereotype.Service;

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

    public void register(AuthDTO authDTO) {
        if (authorizationRepository.findByEmail(authDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        String hashPassword = PasswordUtil.hashPassword(authDTO.getPassword());

        AuthorizationEntity newRegistry = new AuthorizationEntity();
        newRegistry.setEmail(authDTO.getEmail());
        newRegistry.setPassword(hashPassword);

        authorizationRepository.save(newRegistry);

    }
}

