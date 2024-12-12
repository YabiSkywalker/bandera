package com.example.bandera.Services;

import com.example.bandera.Entities.TicketEntity;

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



    public String reconciler(TicketEntity ticket) {



        double finalizedSubtotal = 0.0; //compare against subtotal from the frontend client

        double partsTotal = 0.0; //summation of all the parts and their quantities per serviceEntity

        for (int i = 0; i < ticket.getServiceEntity().size(); i++) {
            for(int j = 0; j < ticket.getServiceEntity().get(i).getPartsRequired().size(); j++) {
                    /* 'i' represents each serviceEntity */
                    /* 'j' represents each serviceEntity */

                partsTotal = ticket.getServiceEntity().get(i).getPartsRequired().indexOf(1) * ticket.getServiceEntity().get(i).getPartsRequired().indexOf(2);
            }
        }




        return "SUCCESS";

    }

}
