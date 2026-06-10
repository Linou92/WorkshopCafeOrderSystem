package se.lexicon;

import java.util.Scanner;

public class CafeApp {

    static Scanner scanner = new Scanner(System.in);
    static String[] items = {"Espresso", "Cappuccino", "Latte", "Croissant", "Sandwich"};
    static double[] prices = {25.00, 35.00, 40.00, 30.00, 55.00};

    public static String getCustomerName(){
        IO.println("Welcome! What is your name? ");
        return scanner.nextLine();
    }

    public  static void displayCafeMenu() {
        IO.println("=============================\n" +
                "       Lexicon Cafe\n" +
                "=============================");
        for(int i = 0; i < items.length; i++) {
            IO.println(String.format(
                    "%d. %-15s %6.2f SEK",
                    i+1,
                    items[i],
                    prices[i]));
        }
        IO.println("=============================\n");
    }

    public static int getItemChoice() {
        IO.println("Enter item number (1-5): ");
        return scanner.nextInt();
    }

    public static int getQuantity() {
        IO.println("How many? ");
        return scanner.nextInt();
    }

    public static boolean isMember() {
        scanner.nextLine();
        IO.println("Loyalty member? (yes/no): ");
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("yes");
    }

    public static double calculateSubtotal(double unitPrice, int quantity){
        return unitPrice * quantity;
    }

    public static double calculateDiscount(double subtotal, boolean member){
        // if member, get 15% off
        if(member){
            return subtotal * 0.15;
        }
        // else if order > 150 SEK get 10% off
        if(subtotal > 150){
            return subtotal * 0.10;
        }
        return 0;
    }

    public static double calculateVat(double priceAfterDiscount){
        // 12% VAT applied after discount
        return priceAfterDiscount * 0.12;
    }

}
