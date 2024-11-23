package grave_escape.game;

import grave_escape.levels.Difficulty;
import grave_escape.levels.GameMode;
import grave_escape.levels.Level1Easy;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

public class HandleGameOverTest {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Game game;

    @Before
    public void setUp(){
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
    }

    @Test
    public void testHandleGameOverPracticeMode(){
        try (var mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(any(), anyString())).thenAnswer(invocation -> null);

            game = new Game(cardLayout, mainPanel, Difficulty.EASY, GameMode.PRACTICE, new Level1Easy());

            // Simulate game over
            game.handleGameOver();

            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(any(), eq("Game Over")), times(1));

            CardLayout layout = (CardLayout) mainPanel.getLayout();
            assertDoesNotThrow(() -> layout.show(mainPanel, "Menu"));

            assertFalse(gameOverField());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHandleGameOverCampaignModeLivesRemaining(){
        try (var mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(any(), anyString())).thenAnswer(invocation -> null);

            game = new Game(cardLayout, mainPanel, Difficulty.EASY, GameMode.CAMPAIGN, new Level1Easy());

            // Simulate game over
            game.handleGameOver();

            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(any(), eq("2 lives remaining.")), times(1));

            CardLayout layout = (CardLayout) mainPanel.getLayout();
            assertDoesNotThrow(() -> layout.show(mainPanel, "Game"));

            assertFalse(gameOverField());

            game.handleGameOver();

            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(any(), eq("1 lives remaining.")), times(1));

            assertDoesNotThrow(() -> layout.show(mainPanel, "Game"));

            assertFalse(gameOverField());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHandleGameOverCampaignModeNoLivesRemaining(){
        try (var mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(any(), anyString())).thenAnswer(invocation -> null);

            game = new Game(cardLayout, mainPanel, Difficulty.EASY, GameMode.CAMPAIGN, new Level1Easy());

            // Simulate loss of 2 lives
            game.handleGameOver();
            CardLayout layout = (CardLayout) mainPanel.getLayout();
            assertDoesNotThrow(() -> layout.show(mainPanel, "Game"));

            game.handleGameOver();
            assertDoesNotThrow(() -> layout.show(mainPanel, "Game"));

            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(any(), anyString()), times(2));

            // Last live lost will produce different message dialog
            game.handleGameOver();

            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(any(), eq("No more lives. Game Over!")), times(1));

            assertDoesNotThrow(() -> layout.show(mainPanel, "Menu"));

            assertFalse(gameOverField());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Helper methods to access private attributes for testing
    private void setGameField(String fieldName, Object value) throws Exception{
        var field = Game.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(game, value);
    }

    private boolean gameOverField() throws Exception{
        var field = Game.class.getDeclaredField("gameOver");
        field.setAccessible(true);
        return field.getBoolean(game);
    }
}
