package multiLevelParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingSlot {
    private String slotID;
    private SlotType slotType;
    private boolean isOccupied;
    private Map<Integer, Integer> gateDistances; // GateID -> Distance

    public ParkingSlot(String slotID, SlotType slotType) {
        this.slotID = slotID;
        this.slotType = slotType;
        this.isOccupied = false;
        this.gateDistances = new HashMap<>();
    }

    public void addDistance(int gateID, int distance) { gateDistances.put(gateID, distance); }
    public int getDistance(int gateID) { return gateDistances.getOrDefault(gateID, Integer.MAX_VALUE); }
    
    // Getters and Seters
    public String getSlotID() { return slotID; }
    public SlotType getSlotType() { return slotType; }
    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }
}