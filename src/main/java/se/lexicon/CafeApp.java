package se.lexicon;

import java.util.Scanner;

public class CafeApp {

    public static String getCustomerName(){
        Scanner scanner = new Scanner(System.in);
        IO.println("Welcome! What is your name? ");
        return scanner.nextLine();
    }

    public  static void displayCafeMenu() {
        String[] items = {"Espresso", "Cappuccino", "Latte", "Croissant", "Sandwich"};
        double[] prices = {25.00, 35.00, 40.00, 30.00, 55.00};
        IO.println("=============================\n" +
                "       Lexicon Cafe\n" +
                "=============================\n");
        for(int i = 0; i < items.length; i++) {
            IO.println(String.format(
                    "%d. %-15s %6.2f SEK",
                    i+1,
                    items[i],
                    prices[i]));
        }
        IO.println("=============================");
    }
}
