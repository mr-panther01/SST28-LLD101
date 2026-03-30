package movieTicketBookingSystem.models;
import movieTicketBookingSystem.enums.BookingStatus;
import java.time.LocalDateTime;
import java.util.List;
public class Booking {
    String id;
    String userId;
    String showId;
    List<ShowSeat> bookedSeats;
    double totalAmount;
    BookingStatus status;
    LocalDateTime bookingTime;

    public Booking() {
    }

    public Booking(String id, String userId, String showId, List<ShowSeat> bookedSeats, double totalAmount, BookingStatus status, LocalDateTime bookingTime) {
        this.id = id;
        this.userId = userId;
        this.showId = showId;
        this.bookedSeats = bookedSeats;
        this.totalAmount = totalAmount;
        this.status = status;
        this.bookingTime = bookingTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public List<ShowSeat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<ShowSeat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}