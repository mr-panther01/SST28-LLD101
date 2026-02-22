
public class BillingCalculator {
    public BillingResult calculate(String customerType, double subtotal, int distinctLines) {
        double taxPct = TaxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = DiscountRules.discountAmount(customerType, subtotal, distinctLines);
        double total = subtotal + tax - discount;
        
        return new BillingResult(subtotal, tax, discount, total, taxPct);
    }
}
