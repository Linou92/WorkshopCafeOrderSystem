package se.lexicon;

import java.util.Scanner;

public class CafeApp {

    public static void greetUser(){
        IO.println("Welcome! What is your name? ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        IO.println("Hi " + name + "!");
    }
}
