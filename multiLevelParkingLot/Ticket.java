package multiLevelParkingLot;

import java.time.LocalDateTime;

public class Ticket {
    private String ticketID;
    private Vehicle vehicle;
    private ParkingSlot assignedSlot;
    private LocalDateTime entryTime;

    public Ticket(String ticketID, Vehicle vehicle, ParkingSlot slot, LocalDateTime entryTime) {
        this.ticketID = ticketID;
        this.vehicle = vehicle;
        this.assignedSlot = slot;
        this.entryTime = entryTime;
    }

    public ParkingSlot getAssignedSlot() { return assignedSlot; }
    public LocalDateTime getEntryTime() { return entryTime; }
}