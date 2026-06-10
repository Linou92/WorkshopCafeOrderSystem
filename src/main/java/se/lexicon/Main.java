package se.lexicon;

import javax.swing.SwingUtilities;

public class Main {

    public static void main() {
        SwingUtilities.invokeLater(() -> {
            new CafeGUI();
        });
    }
}