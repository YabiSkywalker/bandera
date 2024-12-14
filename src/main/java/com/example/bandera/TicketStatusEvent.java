package com.example.bandera;

import com.example.bandera.Configuration.TicketStatus;
import org.springframework.context.ApplicationEvent;

public class TicketStatusEvent extends ApplicationEvent {
    private final String ticketId;
    private final TicketStatus oldStatus;
    private final TicketStatus newStatus;



    public TicketStatusEvent(Object source, String ticketId, TicketStatus oldStatus, TicketStatus newStatus) {
        super(source);
        this.ticketId = ticketId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public String getTicketId() {
        return ticketId;
    }

    public TicketStatus getOldStatus() {
        return oldStatus;
    }

    public TicketStatus getNewStatus() {
        return newStatus;
    }

}
