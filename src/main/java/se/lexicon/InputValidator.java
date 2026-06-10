package se.lexicon;

public class InputValidator {

    public int readIntInRange(String prompt, int min, int max) {

        while (true) {
            try {
                IO.print(prompt);
                int value = Integer.parseInt(IO.readln());

                if (value >= min && value <= max) {
                    return value;
                }

                IO.println("❌ Please enter a number between " + min + " and " + max);

            } catch (NumberFormatException e) {
                IO.println("❌ Invalid number. Try again.");
            }
        }
    }

    public int readPositiveInt(String prompt) {

        while (true) {
            try {
                IO.print(prompt);
                int value = Integer.parseInt(IO.readln());

                if (value > 0) {
                    return value;
                }

                IO.println("❌ Value must be greater than 0.");

            } catch (NumberFormatException e) {
                IO.println("❌ Please enter a valid number.");
            }
        }
    }

    public boolean readYesNo(String prompt) {

        while (true) {

            IO.print(prompt);
            String input = IO.readln().trim().toLowerCase();

            if (input.equals("yes")) return true;
            if (input.equals("no")) return false;

            IO.println("❌ Please type 'yes' or 'no'.");
        }
    }

    public static String readName(String prompt) {

        while (true) {

            IO.print(prompt);
            String name = IO.readln().trim();

            if (name.isEmpty()) {
                IO.println("❌ Name cannot be empty.");
                continue;
            }

            if (!name.matches("[a-zA-Z ]+")) {
                IO.println("❌ Name can only contain letters and spaces.");
                continue;
            }

            if (name.length() < 2) {
                IO.println("❌ Name must be at least 2 characters.");
                continue;
            }
            return name;
        }
    }
}