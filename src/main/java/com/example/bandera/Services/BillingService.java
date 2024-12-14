package com.example.bandera.Services;

import com.example.bandera.Repositories.TicketRepository;
import com.example.bandera.entities.TicketEntity;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BillingService {


    /*


{
  "dateTime": "2024-12-12T13:15:27.360Z",
  "services": [
    {
      "serviceType": "string",
      "serviceDetails": "string",
      "partsRequired": [
        {
          "partName": "string",
          "partQty": 0,
          "partPrice": 0
        }
      ]
    }
  ],
  "preliminaryCost": 0,
  "tax": 0,
  "subtotal": 0,
  "ticketStatus": "INIT",
  "customer": {
    "firstName": "string",
    "lastName": "string",
    "phoneNumber": "string",
    "email": "string"
  },
  "vehicle": {
    "year": 0,
    "make": "string",
    "model": "string",
    "vin": "string"
  }
}

*/
    @Autowired
    private TicketRepository ticketRepository;

    public BillingService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    public double reconciler(String id) {
        TicketEntity billingInquiry = new TicketEntity();


        double finalizedSubtotal = 0.0; //compare against subtotal from the frontend client
        double partsTotal = 0.0;



            billingInquiry = ticketRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Ticket not found."));

            //First we loop through all the services
            for (int loopService = 0; loopService < billingInquiry.getServiceEntity().size(); loopService++) {
                //Now we loop through the parts
                for (int loopParts = 0; loopParts < billingInquiry.getServiceEntity().indexOf(loopParts); loopParts++) {
                    partsTotal = billingInquiry.getServiceEntity().indexOf(1) * billingInquiry.getServiceEntity().indexOf(2);
                }
            }




        return partsTotal;

    }

}
