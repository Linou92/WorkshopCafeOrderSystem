package se.lexicon;

import java.util.Scanner;

public class CafeApp {

    static Scanner scanner = new Scanner(System.in);

    public static String getCustomerName(){
        IO.println("Welcome! What is your name? ");
        return scanner.nextLine();
    }

    public  static void displayCafeMenu() {
        String[] items = {"Espresso", "Cappuccino", "Latte", "Croissant", "Sandwich"};
        double[] prices = {25.00, 35.00, 40.00, 30.00, 55.00};
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
}
