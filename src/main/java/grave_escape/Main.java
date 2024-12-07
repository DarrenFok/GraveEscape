package grave_escape;

import javax.swing.SwingUtilities;

import grave_escape.menu.GameMainMenu;

<<<<<<< HEAD
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
=======
import javax.swing.*;

/**
 * The entry point for the Grave Escape application.
 * <p>
 * This class contains the main method, which initializes and launches
 * the game's main menu using the GameMainMenu class.
 * </p>
 */
public class Main {
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
>>>>>>> a824f650e5bbe26dc5f9f02bcafee1c0a19b3116
        SwingUtilities.invokeLater(() -> {
            // Create an instance of the GameMainMenu and make it visible
            GameMainMenu game = new GameMainMenu();
            game.setVisible(true);
        });
    }
}
