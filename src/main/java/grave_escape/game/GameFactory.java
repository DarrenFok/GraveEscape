package grave_escape.game;

import java.awt.CardLayout;

import javax.swing.JPanel;

import grave_escape.levels.Difficulty;
import grave_escape.levels.GameLevel;
import grave_escape.levels.GameMode;
import grave_escape.levels.Level;
import grave_escape.levels.Level1.Level1Easy;
import grave_escape.levels.Level1.Level1Hard;
import grave_escape.levels.Level1.Level1Normal;
import grave_escape.levels.Level2.Level2Easy;
import grave_escape.levels.Level2.Level2Hard;
import grave_escape.levels.Level2.Level2Normal;
import grave_escape.levels.Level3.Level3Easy;
import grave_escape.levels.Level3.Level3Hard;
import grave_escape.levels.Level3.Level3Normal;

/**
 * Extracting logic for creating game levels and difficulties
 * consoling level and object creation based on the difficulty of the level
 */

public class GameFactory {
    CardLayout cardLayout;
    JPanel mainPanel;
    GameMode gameMode;

    /**
    * Initializing the variables
    * @param cardLayout  the layout for switching between panels
    * @param mainPanel   the main menu panel to select the difficulty/mode
    * @param mode        the game mode of the game
    */
    public GameFactory(CardLayout cardLayout, JPanel mainPanel, GameMode mode) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.gameMode = mode;
    }

    /**
    * creation of the game
    * @param difficulty  the difficulty of the game
    * @param level       the level of the game 
    * @return            A new {@code Game} object configured with the given difficulty and level.
    */
    public Game createGame(Difficulty difficulty, GameLevel level) {
        Level lvl = null;
        if(difficulty == Difficulty.EASY) {
            if(level == GameLevel.Level1) {
                lvl = new Level1Easy();
            } else if (level == GameLevel.Level2) {
                lvl = new Level2Easy();
            } else if (level == GameLevel.Level3) {
                lvl = new Level3Easy();
            }
        } else if (difficulty == Difficulty.NORMAL) {
            if(level == GameLevel.Level1) {
                lvl = new Level1Normal();
            } else if (level == GameLevel.Level2) {
                lvl = new Level2Normal();
            } else if (level == GameLevel.Level3) {
                lvl = new Level3Normal();
            }
        } else if (difficulty == Difficulty.HARD) {
            if(level == GameLevel.Level1) {
                lvl = new Level1Hard();
            } else if (level == GameLevel.Level2) {
                lvl = new Level2Hard();
            } else if (level == GameLevel.Level3) {
                lvl = new Level3Hard();
            }
        }
        return new Game(cardLayout, mainPanel, difficulty, gameMode, lvl);
    }
}
