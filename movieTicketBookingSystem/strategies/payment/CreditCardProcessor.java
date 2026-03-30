package movieTicketBookingSystem.strategies.payment;
import movieTicketBookingSystem.enums.PaymentStatus;

public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public PaymentStatus processPayment(double amount, PaymentDetails details) {
        // Implementation for Credit Card API
        return PaymentStatus.SUCCESS;
    }
}