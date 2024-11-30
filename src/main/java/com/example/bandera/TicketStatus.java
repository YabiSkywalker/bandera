package com.example.bandera;

public enum TicketStatus {
    INIT("INIT"),       // Instead of INITIATED
    OPEN("OPEN"),       // Unchanged
    PEND("PEND"),       // Instead of PENDING
    ACTIV("ACTIVE"),   // Instead of UNDERWAY
    LATE("LATE"),       // Instead of DELAYED
    DONE("DONE"),       // Instead of COMPLETED
    DUE("INVOICED"),
    PAID("PAID"),
    VOID("CANCELLED");
    private final String status;

    TicketStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

