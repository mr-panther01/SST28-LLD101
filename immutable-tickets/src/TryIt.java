import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

public class TryIt {
    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1. Creation via Builder (via Service)
        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing");
        System.out.println("Initial: " + t);

        // 2. "Mutation" actually creates new objects
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);

        System.out.println("Original still same: " + t.getPriority()); // LOW
        System.out.println("New instance: " + escalated); // CRITICAL

        // 3. Attempted external mutation
        try {
            escalated.getTags().add("HACKER"); 
        } catch (UnsupportedOperationException e) {
            System.out.println("\nSuccess: Tags list is immutable! Cannot hack.");
        }
        
        // 4. Validation Check
        try {
            IncidentTicket.builder().id("BAD ID").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Validation caught error: " + e.getMessage());
        }
    }
}