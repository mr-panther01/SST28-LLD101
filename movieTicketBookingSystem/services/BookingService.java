package movieTicketBookingSystem.services;

import movieTicketBookingSystem.models.Booking;
import movieTicketBookingSystem.models.ShowSeat;
import movieTicketBookingSystem.providers.SeatLockProvider;
import movieTicketBookingSystem.strategies.payment.PaymentDetails;
import movieTicketBookingSystem.strategies.payment.PaymentProcessor;

import java.util.List;

public class BookingService {
    private SeatLockProvider seatLockProvider;
    private PricingService pricingService;
    private PaymentProcessor paymentProcessor;

    public BookingService(SeatLockProvider seatLockProvider, PricingService pricingService, PaymentProcessor paymentProcessor) {
        this.seatLockProvider = seatLockProvider;
        this.pricingService = pricingService;
        this.paymentProcessor = paymentProcessor;
    }

    public Booking bookTickets(String userId, String showId, List<ShowSeat> selectedSeats) {
        // 1. Check and acquire locks
        if (seatLockProvider != null) {
            // Lock seats using seatLockProvider
        }
        // 2. Calculate dynamic price via PricingService
        if (pricingService != null) {
            // Calculate prices
        }
        // 3. Generate Booking object with PENDING status
        return new Booking();
    }

    public boolean confirmPayment(String bookingId, PaymentDetails paymentDetails) {
        // 1. Verify SeatLockProvider.validateLock()
        if (seatLockProvider != null) {
            // Validate lock
        }
        // 2. Trigger PaymentProcessor
        if (paymentProcessor != null && paymentDetails != null) {
            paymentProcessor.processPayment(0, paymentDetails);
        }
        // 3. Update DB, release locks, and return status
        return true;
    }

    public void cancelBooking(String bookingId) {
        // 1. Process refund
        // 2. Update DB status to AVAILABLE
    }
}
