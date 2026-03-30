package movieTicketBookingSystem.providers;

import java.util.List;
public interface SeatLockProvider {
    boolean lockSeats(String showId, List<String> seatIds, String userId);
    void unlockSeats(String showId, List<String> seatIds, String userId);
    boolean validateLock(String showId, List<String> seatIds, String userId);
}
