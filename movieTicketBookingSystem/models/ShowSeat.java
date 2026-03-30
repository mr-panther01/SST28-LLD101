package movieTicketBookingSystem.models;
import movieTicketBookingSystem.enums.SeatStatus;
import movieTicketBookingSystem.enums.SeatType;
public class ShowSeat {
    String id;
    String showId;
    String seatId;
    SeatType type; 
    SeatStatus status;
    double basePrice;

    public ShowSeat() {
    }

    public ShowSeat(String id, String showId, String seatId, SeatType type, SeatStatus status, double basePrice) {
        this.id = id;
        this.showId = showId;
        this.seatId = seatId;
        this.type = type;
        this.status = status;
        this.basePrice = basePrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
}