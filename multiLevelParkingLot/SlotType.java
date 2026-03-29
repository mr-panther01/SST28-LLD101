package multiLevelParkingLot;

public enum SlotType {
    SMALL(10.0),   // Hourly Rate: 10
    MEDIUM(20.0),  // Hourly Rate: 20
    LARGE(50.0);   // Hourly Rate: 50

    private final double hourlyRate;
    SlotType(double hourlyRate) { this.hourlyRate = hourlyRate; }
    public double getHourlyRate() { return hourlyRate; }
}