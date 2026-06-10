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
        Item     : %s x %d
        Subtotal : %.2f SEK
        %sVAT      : %.2f SEK
        -----------------------------
        TOTAL    : %.2f SEK
        """,
        order.getCustomer().getName(),
        order.getItem().getName(),
        order.getQuantity(),
        subtotal,
        discount > 0 ? String.format("Discount : -%.2f SEK%n", discount) : "",
        vat,
        total)
        );

        displayEndMessage(order.getCustomer().getName());
    }
}
