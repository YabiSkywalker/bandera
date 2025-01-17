package com.example.bandera.Controllers;

import com.example.bandera.DataModels.AuthDTO;
import com.example.bandera.DataModels.EmployeeRegistryDTO;
import com.example.bandera.Services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Controller", description = "GENERATE A BEARER TOKEN FIRST!")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @Operation(summary = "Login to generate token")
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthDTO loginRequest) {
        String token = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        return ResponseEntity.ok(new AuthDTO.AuthResponseDTO(token));
    }

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody EmployeeRegistryDTO employeeInfo) {
        authenticationService.register(employeeInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully");
    }

}
