package grave_escape;

import grave_escape.menu.GameMainMenu;

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
        SwingUtilities.invokeLater(() -> {
            GameMainMenu game = new GameMainMenu();
            game.setVisible(true);
        }) ;
    }
}
