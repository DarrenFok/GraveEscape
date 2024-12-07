package grave_escape;

import javax.swing.SwingUtilities;

import grave_escape.menu.GameMainMenu;

/**
 * The {@code Main} class is the entry point of the Grave Escape game.
 * It initializes and displays the main game menu when the program is run.
 */
public class Main {
    
    /**
     * The main method that is called when the program starts.
     * It runs the game in the Event Dispatch Thread (EDT) using {@code SwingUtilities.invokeLater}.
     * This ensures that the UI components are updated safely from the EDT.
     * 
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create an instance of the GameMainMenu and make it visible
            GameMainMenu game = new GameMainMenu();
            game.setVisible(true);
        });
    }
}
