package se.lexicon;

public class CafeApp {

    Calculator calculator = new Calculator();
    ReceiptPrinter receiptPrinter = new ReceiptPrinter();

    private MenuItem[] menu = {
            new MenuItem("Espresso", 25),
            new MenuItem("Cappuccino", 35),
            new MenuItem("Latte", 40),
            new MenuItem("Croissant", 30),
            new MenuItem("Sandwich", 55)
    };

    public void start() {

        boolean firstCustomer = true;
        int customersServed = 0;
        double totalRevenue = 0;

        while (true) {

            String name = askForCustomer(firstCustomer);
            firstCustomer = false;

            if (name.equalsIgnoreCase("done")) {
                break;
            }

            Order order = takeOrder(name);

            receiptPrinter.printReceipt(order);

            double total = calculateTotal(order);

            customersServed++;
            totalRevenue += total;
        }

        receiptPrinter.printEndReport(customersServed, totalRevenue);
    }

    private String askForCustomer(boolean firstCustomer) {

        if (firstCustomer) {
            IO.println("Welcome! What is your name?");
        } else {
            IO.print("Next customer name (or 'done'): ");
        }

        return IO.readln();
    }

    private Order takeOrder(String name) {

        IO.println("Hi " + name + "! Here is our menu:");

        MenuItem item = selectItem();
        int quantity = getQuantity();
        boolean member = isMember();

        Customer customer = new Customer(name, member);

        return new Order(customer, item, quantity);
    }

    private double calculateTotal(Order order) {

        double subtotal = calculator.subtotal(order);
        double discount = calculator.discount(subtotal, order.getCustomer().isMember());
        double vat = calculator.vat(subtotal - discount);

        return subtotal - discount + vat;
    }

    private MenuItem selectItem() {

        IO.println("=============================\n" +
                "       Lexicon Cafe\n" +
                "=============================");
        for(int i = 0; i < menu.length; i++) {
            IO.println(String.format(
                    "%d. %-15s %6.2f SEK",
                    i+1,
                    menu[i].getName(),
                    menu[i].getPrice()));
        }
        IO.println("=============================\n");

        IO.println("Enter item number (1-5): ");
        int choice = Integer.parseInt(IO.readln());

        return menu[choice - 1];
    }

    private int getQuantity() {
        IO.println("How many? ");
        return Integer.parseInt(IO.readln());
    }

    private boolean isMember() {
        IO.println("Loyalty member? (yes/no): ");
        return IO.readln().equalsIgnoreCase("yes");
    }

}