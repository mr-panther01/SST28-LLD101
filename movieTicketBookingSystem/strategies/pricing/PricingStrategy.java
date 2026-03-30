package movieTicketBookingSystem.strategies.pricing;
import movieTicketBookingSystem.models.Show;
import movieTicketBookingSystem.models.ShowSeat;
public interface PricingStrategy {
    double calculatePrice(Show show, ShowSeat showSeat);
}