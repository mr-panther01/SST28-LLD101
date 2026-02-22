import java.util.*;

public class InvoiceFormatter {
    public String format(String invId, List<OrderLine> lines, Map<String, MenuItem> menu, BillingResult res) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(invId).append("\n");

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            sb.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        sb.append(String.format("Subtotal: %.2f\n", res.subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n", res.taxPct, res.tax));
        sb.append(String.format("Discount: -%.2f\n", res.discount));
        sb.append(String.format("TOTAL: %.2f\n", res.total));
        return sb.toString();
    }
}