package grave_escape;

import javax.swing.SwingUtilities;

import grave_escape.menu.GameMainMenu;

/**
 * The entry point for the Grave Escape application.
 * <p>
 * This class contains the main method, which initializes and launches
 * the game's main menu using the GameMainMenu class.
 * </p>
 */
public class Main {
    /**
     * Default constructor for the {@code Main} class.
     * Initializes the primary components of the application.
     */
    public Main(){
        // No logic Constructor logic
    }
    /**
     * The main method that serves as the entry point for the application.
     * <p>
     * It creates and displays the GameMainMenu in a thread-safe manner
     * by using SwingUtilities.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            // Create an instance of the GameMainMenu and make it visible
            GameMainMenu game = new GameMainMenu();
            game.setVisible(true);
        });
    }
}
