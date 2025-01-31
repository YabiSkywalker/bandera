package com.example.bandera.DataModels.ResponseModels;

import com.example.bandera.Configuration.TicketStatus;

public class SetStatusResponseDTO {

    private String id;
    private TicketStatus ticketStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
