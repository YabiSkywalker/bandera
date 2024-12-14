package com.example.bandera.Controllers;


import com.example.bandera.Services.BillingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class BillingController {

    /* ----------------------------------------------- CRUD FORM ----------------------------------------------------------- */


    @Autowired
    private BillingService billingService;


    @Operation(summary = "Validate subtotal")
    @PostMapping("/reconcile/{id}")
    public Double reconciler(@PathVariable String id) {


        return billingService.reconciler(id);
    }
}
