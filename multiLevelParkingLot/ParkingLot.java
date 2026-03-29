package multiLevelParkingLot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private static ParkingLot instance;
    private List<ParkingSlot> slots;
    private Set<Integer> gates;

    private ParkingLot() {
        slots = new ArrayList<>();
        gates = new HashSet<>();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) instance = new ParkingLot();
        return instance;
    }

    public void addSlot(ParkingSlot slot) { slots.add(slot); }

    public Ticket park(Vehicle vehicle, LocalDateTime entryTime, int gateID) {
        ParkingSlot bestSlot = findNearestCompatibleSlot(vehicle.getType(), gateID);

        if (bestSlot != null) {
            bestSlot.setOccupied(true);
            String ticketID = "TKT-" + System.currentTimeMillis();
            return new Ticket(ticketID, vehicle, bestSlot, entryTime);
        }
        return null;
    }

    private ParkingSlot findNearestCompatibleSlot(VehicleType vType, int gateID) {
        ParkingSlot nearestSlot = null;
        int minDistance = Integer.MAX_VALUE;

        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied() && isCompatible(vType, slot.getSlotType())) {
                int dist = slot.getDistance(gateID);
                if (dist < minDistance) {
                    minDistance = dist;
                    nearestSlot = slot;
                }
            }
        }
        return nearestSlot;
    }

    private boolean isCompatible(VehicleType vType, SlotType sType) {
        switch (vType) {
            case BUS: return sType == SlotType.LARGE;
            case CAR: return sType == SlotType.MEDIUM || sType == SlotType.LARGE;
            case TWO_WHEELER: return true; 
            default: return false;
        }
    }

    public double exit(Ticket ticket, LocalDateTime exitTime) {
        long hours = Duration.between(ticket.getEntryTime(), exitTime).toHours();
        if (Duration.between(ticket.getEntryTime(), exitTime).toMinutes() % 60 > 0) hours++; // Round up
        
        double rate = ticket.getAssignedSlot().getSlotType().getHourlyRate();
        
        ticket.getAssignedSlot().setOccupied(false);
        return hours * rate;
    }
}