package se.lexicon;

public class CafeApp {
    
    InputValidator inputValidator = new InputValidator();
    private CafeService service =  new CafeService();

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

            if (name.equalsIgnoreCase("done")) break;

            IO.println("Hi " + name + "! Here is our menu:");

            displayMenu();

            boolean member = isMember();

            Customer customer = new Customer(name, member);
            Order order = new Order(customer);

            takeItems(order);

            service.printReceipt(order);

            double total = service.calculateTotal(order);

            customersServed++;
            totalRevenue += total;
        }

        service.printEndReport(customersServed, totalRevenue);
    }

    private String askForCustomer(boolean firstCustomer) {

        if (firstCustomer) {
            return InputValidator.readName("Welcome! What is your name? ");
        } else {
            return InputValidator.readName("Next customer name (or 'done'): ");
        }
    }

    private void displayMenu() {

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
    }

    private int getQuantity() {
        return inputValidator.readPositiveInt("How many? ");
    }

    private boolean isMember() {
        return inputValidator.readYesNo("Loyalty member? (yes/no): ");
    }

    private void takeItems(Order order) {

        while (true) {

            int choice = inputValidator.readIntInRange(
                    "Enter item number (1-5, or 0 to finish): ",
                    0,
                    menu.length
            );

            if (choice == 0) {
                break;
            }

            int quantity = getQuantity();
            MenuItem item = menu[choice - 1];
            order.addItem(new LineItem(item, quantity));
            IO.println(item.getName() + " added.");
        }
    }

}