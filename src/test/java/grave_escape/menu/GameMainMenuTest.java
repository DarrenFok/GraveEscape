package grave_escape.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class GameMainMenuTest {
    private GameMainMenu gameMainMenu;

    @BeforeEach
    public void setUp() {
        gameMainMenu = new GameMainMenu();
    }

    @Test
    public void testTitleSetCorrectly(){
        assertEquals("Grave Escape", gameMainMenu.getTitle());
    }

    @Test
    public void testMainPanelInitialization(){
        JPanel mainPanel = (JPanel) gameMainMenu.getContentPane();
        assertNotNull(mainPanel, "Main panel should be initialized");
        assertEquals(CardLayout.class, mainPanel.getLayout().getClass(), "Main panel should use CardLayout");
    }

    @Test
    public void testMenuPanelContainsButtons(){
        JPanel mainPanel = (JPanel) gameMainMenu.getContentPane().getComponent(0);
        assertNotNull(mainPanel.getComponent(0), "Title label should be added to the menu panel");

        JButton campaignButton = (JButton) mainPanel.getComponent(1);
        JButton practiceButton = (JButton) mainPanel.getComponent(2);
        JButton settingsButton = (JButton) mainPanel.getComponent(3);
        JButton quitButton = (JButton) mainPanel.getComponent(4);

        assertEquals("Campaign", campaignButton.getText());
        assertEquals("Practice", practiceButton.getText());
        assertEquals("Settings", settingsButton.getText());
        assertEquals("Quit", quitButton.getText());
    }

    @Test
    void testShowPanel() throws Exception {
        // Use reflection to access private mainPanel field
        Field mainPanelField = GameMainMenu.class.getDeclaredField("mainPanel");
        mainPanelField.setAccessible(true);
        JPanel mainPanel = (JPanel) mainPanelField.get(gameMainMenu);

        // Call showPanel to display the "Campaign" panel
        gameMainMenu.showPanel("Campaign");

        // Verify that the Campaign panel is now visible
        boolean campaignPanelVisible = isPanelVisible(mainPanel, "Campaign");
        assertTrue(campaignPanelVisible, "The Campaign panel should be visible.");
    }

    @Test
    void testCampaignButtonAction() throws Exception {
        JButton campaignButton = getButtonByName("Campaign");
        campaignButton.doClick();
        assertVisiblePanel("Campaign");
    }

    @Test
    void testPracticeButtonAction() throws Exception {
        JButton practiceButton = getButtonByName("Practice");
        practiceButton.doClick();
        assertVisiblePanel("Practice");
    }

    @Test
    void testSettingsButtonAction() throws Exception {
        JButton settingsButton = getButtonByName("Settings");
        settingsButton.doClick();
        assertVisiblePanel("Settings");
    }

    @Test
    void testFontLoadingFallback() {
        JPanel menuPanel = (JPanel) gameMainMenu.getContentPane().getComponent(0);
        JLabel titleLabel = (JLabel) menuPanel.getComponent(0);

        assertNotNull(titleLabel.getFont(), "Title label font should be set.");

        // If the custom font fails to load, check if fallback font is used
        String expectedFallbackFont = "Arial";
        assertTrue(
                titleLabel.getFont().getFontName().equals("Storm Gust") || titleLabel.getFont().getFontName().equals(expectedFallbackFont),
                "Font should fall back to Arial if the custom font fails."
        );
    }

    // Helper function to retrieve a button by its name
    private JButton getButtonByName(String name) {
        JPanel menuPanel = (JPanel) gameMainMenu.getContentPane().getComponent(0);
        for (Component component : menuPanel.getComponents()) {
            if (component instanceof JButton && ((JButton) component).getText().equals(name)) {
                return (JButton) component;
            }
        }
        throw new IllegalArgumentException("Button not found: " + name);
    }

    // Helper function to check the visible panel
    private void assertVisiblePanel(String expectedPanelName) throws Exception {
        // Access the mainPanel field using reflection
        Field mainPanelField = GameMainMenu.class.getDeclaredField("mainPanel");
        mainPanelField.setAccessible(true);
        JPanel mainPanel = (JPanel) mainPanelField.get(gameMainMenu);

        // Get the CardLayout from mainPanel
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();

        // Verify the name of the currently visible panel
        String actualVisiblePanelName = null;
        for (Component comp : mainPanel.getComponents()) {
            if (comp.isVisible()) {
                actualVisiblePanelName = ((JPanel) comp).getName();  // Get the name assigned to the panel
                break;
            }
        }

        assertEquals(expectedPanelName, actualVisiblePanelName,
                "Expected the visible panel to be: " + expectedPanelName);
    }

    private boolean isPanelVisible(JPanel mainPanel, String panelName) {
        for (Component comp : mainPanel.getComponents()) {
            if (comp.isVisible() && comp.getName().equals(panelName)) {
                return true;
            }
        }
        return false;
    }
}
