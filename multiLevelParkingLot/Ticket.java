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

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSlot getAssignedSlot() { return assignedSlot; }

    public void setAssignedSlot(ParkingSlot assignedSlot) {
        this.assignedSlot = assignedSlot;
    }

    public LocalDateTime getEntryTime() { return entryTime; }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }
}