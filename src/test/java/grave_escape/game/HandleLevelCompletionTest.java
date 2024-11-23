package grave_escape.game;

import grave_escape.levels.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.CardLayout;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HandleLevelCompletionTest {
    private Game game;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    @BeforeAll
    static void setUpHeadless(){
        System.setProperty("java.awt.headless", "true");
    }

    @BeforeEach
    public void setUp() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
    }

    @Test
    public void testHandleLevelCompletionPracticeMode(){
        // Mock JOptionPane
        try (var mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(any(), anyString())).thenAnswer(invocation -> null);

            game = new Game(cardLayout, mainPanel, Difficulty.EASY, GameMode.PRACTICE, new Level1Easy());
            game.handleLevelCompletion();

            // Verify that JOptionPane.showMessageDialog was called
            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(any(), anyString()));

            CardLayout layout = (CardLayout) mainPanel.getLayout();
            assertDoesNotThrow(() -> layout.show(mainPanel, "Menu"));
        }
    }

    @Test
    public void testHandleLevelCompletionCampaignModeNextLevel(){
        try (var mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(any(), anyString())).thenAnswer(invocation -> null);

            Level nextLevel = new Level2Easy();
            game = new Game(cardLayout, mainPanel, Difficulty.EASY, GameMode.CAMPAIGN, new Level1Easy());
            game.handleLevelCompletion();

            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(any(), anyString()));

            assertTrue(nextLevel instanceof Level2Easy, "Game should have transitioned to next level");

            CardLayout layout = (CardLayout) mainPanel.getLayout();
            assertDoesNotThrow(() -> layout.show(mainPanel, "Game"));
        }
    }

    @Test
    public void testHandleLevelCompletionCampaignModeEndGame(){
        try (var mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(any(), anyString())).thenAnswer(invocation -> null);

            game = new Game(cardLayout, mainPanel, Difficulty.EASY, GameMode.CAMPAIGN, new Level1Easy());
            // Simulate completing all three levels
            game.handleLevelCompletion();
            game.handleLevelCompletion();
            game.handleLevelCompletion();

            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(any(), anyString()), times(3));

            CardLayout layout = (CardLayout) mainPanel.getLayout();
            assertDoesNotThrow(() -> layout.show(mainPanel, "Menu"));
        }
    }

    @Test
    public void testHandleLevelCompletionInvalidGameMode(){
        game = new Game(cardLayout, mainPanel, Difficulty.EASY, null, new Level1Easy());
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> game.handleLevelCompletion(),
                "Expected initializeLevels to throw IllegalArgumentException"
        );
        assertEquals("Invalid game mode", exception.getMessage());
    }
}
