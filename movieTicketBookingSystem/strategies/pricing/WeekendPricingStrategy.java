package movieTicketBookingSystem.strategies.pricing;

import movieTicketBookingSystem.models.Show;
import movieTicketBookingSystem.models.ShowSeat;
public class WeekendPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Show show, ShowSeat showSeat) {
        return showSeat.getBasePrice() * 1.20; 
    }
}