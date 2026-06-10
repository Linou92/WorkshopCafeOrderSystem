package se.lexicon;

public class ReceiptPrinter {

    private void displayEndMessage(String name) {
        IO.println("=============================\n" +
                "     Thank you, " + name +"!\n" +
                "     See you next time.\n" +
                "=============================");
    }

    public void printEndReport(int customers, double revenue) {

        IO.println("""
                ==============================
                      END OF DAY REPORT
                ==============================
                """);

        IO.println("Customers served : " + customers);
        IO.println("Total revenue    : " + String.format("%.2f SEK", revenue));
        IO.println("==============================");
    }

    public void printReceipt(Order order) {

        Calculator calculator = new Calculator();
        double subtotal = calculator.subtotal(order);
        double discount = calculator.discount(subtotal, order.getCustomer().isMember());
        double vat = calculator.vat(subtotal - discount);
        double total = subtotal - discount + vat;

        IO.println(String.format("""
        =============================
                LEXICON CAFE
        =============================
        Customer : %s
        -----------------------------
        """,
        order.getCustomer().getName()));
        for (LineItem line : order.getItems()) {
            IO.println(String.format(
                    "  %-15s x%-3d %8.2f SEK",
                    line.getItem().getName(),
                    line.getQuantity(),
                    line.lineTotal()
            ));
        }
        IO.println(String.format("""
        -----------------------------
        Subtotal : %.2f SEK
        %sVAT      : %.2f SEK
        -----------------------------
        TOTAL    : %.2f SEK
        """,
        subtotal,
        discount > 0 ? String.format("Discount : -%.2f SEK%n", discount) : "",
        vat,
        total)
        );

        displayEndMessage(order.getCustomer().getName());
    }
}
