package se.lexicon;

public class ReceiptPrinter {

    private Calculator calculator = new Calculator();

    public String buildReceipt(Order order) {

        double subtotal = calculator.subtotal(order);
        double discount = calculator.discount(subtotal, order.getCustomer().isMember());
        double vat = calculator.vat(subtotal - discount);
        double total = subtotal - discount + vat;

        StringBuilder sb = new StringBuilder();

        sb.append("""
                =============================
                        LEXICON CAFE
                =============================
                Customer : %s
                ---------------------------------------
                """.formatted(order.getCustomer().getName()));

        for (LineItem line : order.getItems()) {

            sb.append(String.format(
                    "  %-15s x%-3d %8.2f SEK%n",
                    line.getItem().getName(),
                    line.getQuantity(),
                    line.lineTotal()
            ));
        }

        sb.append(String.format("""
                --------------------------------------
                Subtotal : %.2f SEK
                """, subtotal));

        if (discount > 0) {
            sb.append(String.format("Discount : -%.2f SEK%n", discount));
        }

        sb.append(String.format("""
                VAT      : %.2f SEK
                --------------------------------------
                TOTAL    : %.2f SEK
                =============================
                """, vat, total));

        sb.append("\nThank you, ")
                .append(order.getCustomer().getName())
                .append("!\nSee you next time.\n" +
                        "=============================");

        return sb.toString();
    }

    public String buildEndReport(int customersServed, double revenue) {

        return """
            ==============================
                  END OF DAY REPORT
            ==============================
            Customers served : %d
            Total revenue    : %.2f SEK
            ==============================
            """.formatted(customersServed, revenue);
    }
}