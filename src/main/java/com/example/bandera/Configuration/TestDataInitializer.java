package com.example.bandera.Configuration;

//This class is ONLY to ingest minimum ref data (enough to get a bearer token for login)
/*
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


        // Save to MongoDB
        authorizationRepository.save(testUser);
    }
}

 */