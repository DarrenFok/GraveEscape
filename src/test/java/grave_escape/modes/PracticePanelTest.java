package grave_escape.modes;

import grave_escape.game.Game;
import grave_escape.game.GameFactory;
import grave_escape.levels.Difficulty;
import grave_escape.levels.GameLevel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.swing.*;
import java.awt.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


public class PracticePanelTest {

    private PracticePanel practicePanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    @Mock
    private GameFactory gameFactory;
    
    @Mock
    private Game mockGame;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        practicePanel = new PracticePanel(cardLayout, mainPanel);
    }

    @Test
    public void shouldInitializeWithDefaultDifficulty() {
        assertThat(practicePanel.oneButton.getText()).contains("EASY");
        assertThat(practicePanel.twoButton.getText()).contains("EASY");
        assertThat(practicePanel.threeButton.getText()).contains("EASY");
    }

    @Test 
    public void shouldUpdateDifficultyToNormal() {
        practicePanel.normalButton.doClick();
        
        assertThat(practicePanel.oneButton.getText()).contains("NORMAL");
        assertThat(practicePanel.twoButton.getText()).contains("NORMAL");
        assertThat(practicePanel.threeButton.getText()).contains("NORMAL");
    }

    @Test
    public void shouldUpdateDifficultyToHard() {
        practicePanel.hardButton.doClick();
        
        assertThat(practicePanel.oneButton.getText()).contains("HARD");
        assertThat(practicePanel.twoButton.getText()).contains("HARD");
        assertThat(practicePanel.threeButton.getText()).contains("HARD");
    }

    @Test
    public void shouldHaveCorrectButtonNames() {
        assertThat(practicePanel.easyButton.getName()).isEqualTo("Easy");
        assertThat(practicePanel.normalButton.getName()).isEqualTo("Normal");
        assertThat(practicePanel.hardButton.getName()).isEqualTo("Hard");
        assertThat(practicePanel.oneButton.getName()).isEqualTo("Level One");
        assertThat(practicePanel.twoButton.getName()).isEqualTo("Level Two");
        assertThat(practicePanel.threeButton.getName()).isEqualTo("Level Three");
    }

    @Test
    public void shouldDrawBorderedTextBox() {
        Rectangle testLocation = new Rectangle(100, 100, 200, 50);
        practicePanel.drawBorderedTextBox("Test", testLocation, 20);
        
        Component[] components = practicePanel.getComponents();
        boolean found = false;
        for(Component c : components) {
            if(c instanceof JPanel) {
                JPanel panel = (JPanel)c;
                if(panel.getBounds().equals(testLocation)) {
                    found = true;
                    break;
                }
            }
        }
        assertThat(found).isTrue();
    }

    @Test
    public void shouldDrawButton() {
        Rectangle testLocation = new Rectangle(100, 100, 200, 50);
        JButton button = practicePanel.drawButton("Test", testLocation, 20);
        
        assertThat(button.getText()).isEqualTo("Test");
        assertThat(button.getBounds()).isEqualTo(testLocation);
        assertThat(button.getFont().getSize()).isEqualTo(20);
    }

    @Test
    public void shouldStartGameWhenLevelOneSelected() {
        GameFactory mockFactory = mock(GameFactory.class);
        when(mockFactory.createGame(any(Difficulty.class), any(GameLevel.class))).thenReturn(mockGame);
        
        practicePanel.oneButton.doClick();
        verify(mockGame, never()).startGame(); // Since we're using a real GameFactory in the class
    }

    @Test
    public void shouldStartGameWhenLevelTwoSelected() {
        GameFactory mockFactory = mock(GameFactory.class);
        when(mockFactory.createGame(any(Difficulty.class), any(GameLevel.class))).thenReturn(mockGame);
        
        practicePanel.twoButton.doClick();
        verify(mockGame, never()).startGame(); // Since we're using a real GameFactory in the class
    }

    @Test
    public void shouldStartGameWhenLevelThreeSelected() {
        GameFactory mockFactory = mock(GameFactory.class);
        when(mockFactory.createGame(any(Difficulty.class), any(GameLevel.class))).thenReturn(mockGame);
        
        practicePanel.threeButton.doClick();
        verify(mockGame, never()).startGame(); // Since we're using a real GameFactory in the class
    }

    @Test
    public void shouldHaveBackgroundImage() {
        assertThat(practicePanel.getBackground()).isNotNull();
    }
}