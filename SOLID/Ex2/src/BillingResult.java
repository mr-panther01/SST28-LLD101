public class BillingResult {
    public final double subtotal;
    public final double tax;
    public final double discount;
    public final double total;
    public final double taxPct;

    public BillingResult(double subtotal, double tax, double discount, double total, double taxPct) {
        this.subtotal = subtotal; this.tax = tax; this.discount = discount; 
        this.total = total; this.taxPct = taxPct;
    }
}