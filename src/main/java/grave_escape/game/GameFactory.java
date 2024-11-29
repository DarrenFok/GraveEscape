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

public class GameFactory {
    CardLayout cardLayout;
    JPanel mainPanel;
    GameMode gameMode;
    public GameFactory(CardLayout cardLayout, JPanel mainPanel, GameMode mode) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.gameMode = mode;
    }

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
