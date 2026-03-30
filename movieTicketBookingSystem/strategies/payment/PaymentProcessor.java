package movieTicketBookingSystem.strategies.payment;
import movieTicketBookingSystem.enums.PaymentStatus;

public interface PaymentProcessor {
    PaymentStatus processPayment(double amount, PaymentDetails details);
}