package se.lexicon;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        // greet user
        String name = CafeApp.getCustomerName();
        IO.println("Hi " + name + "! Here is our menu:");

        // display menu
        CafeApp.displayCafeMenu();

        // take order
        int itemChoice = CafeApp.getItemChoice();
        int quantity = CafeApp.getQuantity();
        boolean member = CafeApp.isMember();

        // calculate prices and discounts
        String itemName = CafeApp.items[itemChoice-1]; // get selected item
        double unitPrice = CafeApp.prices[itemChoice-1]; // get its price
        double subtotal = CafeApp.calculateSubtotal(unitPrice, quantity);
        double discount = CafeApp.calculateDiscount(subtotal, member);
        double vat = CafeApp.calculateVat(subtotal - discount);
        double total = subtotal - discount + vat;

        // print receipt
        CafeApp.printReceipt(name, itemName, quantity, subtotal, discount, vat, total);

        // display end message
        CafeApp.displayEndMessage(name);
    }

}
