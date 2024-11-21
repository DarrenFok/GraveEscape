package grave_escape;

import grave_escape.menu.GameMainMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            GameMainMenu game = new GameMainMenu();
            game.setVisible(true);
        }) ;
    }
}
