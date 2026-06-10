package se.lexicon;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        // greet user
        String name = CafeApp.getCustomerName();
        IO.println("Hi " + name + "! Here is our menu:");
        CafeApp.displayCafeMenu();
        int itemChoice = CafeApp.getItemChoice();
        int quantity = CafeApp.getQuantity();
        boolean member = CafeApp.isMember();
    }
}
