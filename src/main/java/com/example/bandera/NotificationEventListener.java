package com.example.bandera;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventListener {
    private final NotificationService notificationService;
    public NotificationEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @EventListener
    public void handStatusChange(TicketStatusEvent event) {


        String message = String.format(
                "Ticket: %s status updated from '%s' to '%s'",
                event.getTicketId(), event.getOldStatus(), event.getNewStatus()
        );
        notificationService.sendNotification(message);
    }

    }

