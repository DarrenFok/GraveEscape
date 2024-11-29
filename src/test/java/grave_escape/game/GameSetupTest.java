package grave_escape.game;

import grave_escape.levels.*;
import grave_escape.levels.Level1.Level1Easy;
import grave_escape.levels.Level1.Level1Hard;
import grave_escape.levels.Level1.Level1Normal;
import grave_escape.levels.Level2.Level2Easy;
import grave_escape.levels.Level2.Level2Hard;
import grave_escape.levels.Level2.Level2Normal;
import grave_escape.levels.Level3.Level3Easy;
import grave_escape.levels.Level3.Level3Hard;
import grave_escape.levels.Level3.Level3Normal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameSetupTest {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Game game;

    @BeforeEach
    void setUp() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
    }

    @Test
    public void testInitializeLevelsEasy(){
        game = new Game(cardLayout, mainPanel, Difficulty.EASY, GameMode.CAMPAIGN, new Level1Easy());
        List<Level> levels = game.getLevels();
        assertEquals(2, levels.size(), "Easy difficulty should have an additional 2 levels");
        assertTrue(levels.get(0) instanceof Level2Easy, "Second level should be instance of Level2Easy");
        assertTrue(levels.get(1) instanceof Level3Easy, "Third level should be instance of Level3Easy");
    }

    @Test
    public void testInitializeLevelsNormal(){
        game = new Game(cardLayout, mainPanel, Difficulty.NORMAL, GameMode.CAMPAIGN, new Level1Normal());
        List<Level> levels = game.getLevels();
        assertEquals(2, levels.size(), "Normal difficulty should have an additional 2 levels");
        assertTrue(levels.get(0) instanceof Level2Normal, "Second level should be instance of Level2Normal");
        assertTrue(levels.get(1) instanceof Level3Normal, "Third level should be instance of Level3Normal");
    }

    @Test
    public void testInitializeLevelsHard(){
        game = new Game(cardLayout, mainPanel, Difficulty.HARD, GameMode.CAMPAIGN, new Level1Hard());
        List<Level> levels = game.getLevels();
        assertEquals(2, levels.size(), "Hard difficulty should have an additional 2 levels");
        assertTrue(levels.get(0) instanceof Level2Hard, "Second level should be instance of Level2Hard");
        assertTrue(levels.get(1) instanceof Level3Hard, "Third level should be instance of Level3Hard");
    }

    @Test
    public void testInitializeLevelsIllegal(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> game = new Game(cardLayout, mainPanel, null, GameMode.CAMPAIGN, new Level1Hard()),
                "Expected initializeLevels to throw IllegalArgumentException"
        );
        assertEquals("Invalid difficulty", exception.getMessage());
    }
}
