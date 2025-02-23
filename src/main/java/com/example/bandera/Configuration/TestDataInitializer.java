package com.example.bandera.Configuration;

//This class is ONLY to ingest minimum ref data (enough to get a bearer token for login)

import com.example.bandera.Repositories.AuthorizationRepository;
import com.example.bandera.entities.AuthorizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestDataInitializer implements CommandLineRunner {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Sample test data
        String testEmail = "test@example.com";
        String testPassword = encoder.encode("password123");  // Hashed password

        AuthorizationEntity testUser = new AuthorizationEntity();
        testUser.setEmail(testEmail);
        testUser.setPassword(testPassword);

        authorizationRepository.save(testUser);
    }
}