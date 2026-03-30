package movieTicketBookingSystem.services;
import movieTicketBookingSystem.models.Show;
import movieTicketBookingSystem.models.ShowSeat;
import movieTicketBookingSystem.strategies.pricing.PricingStrategy;

import java.util.List;

public class PricingService {
    List<PricingStrategy> activeStrategies;

    public double getFinalPrice(Show show, ShowSeat showSeat) {
        double currentPrice = showSeat.getBasePrice();
        for (PricingStrategy strategy : activeStrategies) {
             currentPrice = Math.max(currentPrice, strategy.calculatePrice(show, showSeat));
        }
        return currentPrice;
    }
}