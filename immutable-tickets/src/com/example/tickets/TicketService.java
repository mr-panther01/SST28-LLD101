package com.example.tickets;

public class TicketService {

    public IncidentTicket createTicket(String id, String reporter, String title) {
        return IncidentTicket.builder()
                .id(id)
                .reporterEmail(reporter)
                .title(title)
                .build();
    }

    public IncidentTicket assign(IncidentTicket ticket, String assigneeEmail) {
        // Return a NEW instance using toBuilder()
        return ticket.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket ticket) {
        return ticket.toBuilder()
                .priority("CRITICAL")
                .build();
    }
}