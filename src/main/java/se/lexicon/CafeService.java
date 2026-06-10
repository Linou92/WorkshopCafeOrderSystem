package se.lexicon;

public class CafeService {

    private Calculator calculator = new Calculator();
    private ReceiptPrinter receiptPrinter = new ReceiptPrinter();

    public double calculateTotal(Order order) {

        double subtotal = calculator.subtotal(order);
        double discount = calculator.discount(subtotal, order.getCustomer().isMember());
        double vat = calculator.vat(subtotal - discount);

        return subtotal - discount + vat;
    }

    public void printReceipt(Order order) {
        String receipt = receiptPrinter.buildReceipt(order);
        IO.println(receipt); // if console, or return to GUI
    }

    public void printEndReport(int customersServed, double revenue) {
            String report = receiptPrinter.buildEndReport(customersServed, revenue);
            IO.println(report); // console version
    }
}