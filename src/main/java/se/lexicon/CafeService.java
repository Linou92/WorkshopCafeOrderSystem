package se.lexicon;

public class CafeService {

    private Calculator calculator = new Calculator();
    private ReceiptPrinter receiptPrinter = new ReceiptPrinter();

    public Order takeOrder(String name, MenuItem item, int quantity, boolean member) {
        Customer customer = new Customer(name, member);
        return new Order(customer, item, quantity);
    }

    public double calculateTotal(Order order) {

        double subtotal = calculator.subtotal(order);
        double discount = calculator.discount(subtotal, order.getCustomer().isMember());
        double vat = calculator.vat(subtotal - discount);

        return subtotal - discount + vat;
    }

    public void printReceipt(Order order) {
        receiptPrinter.printReceipt(order);
    }

    public void printEndReport(int customersServed, double revenue) {
        receiptPrinter.printEndReport(customersServed, revenue);
    }
}