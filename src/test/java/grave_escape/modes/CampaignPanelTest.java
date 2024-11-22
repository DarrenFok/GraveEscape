package grave_escape.modes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;
import java.awt.*;

public class CampaignPanelTest {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private CampaignPanel campaignPanel;

    @Before
    public void setUp() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        campaignPanel = new CampaignPanel(cardLayout, mainPanel);
    }

    @Test
    public void testPanelInitialization() {
        assertNotNull("Campaign panel should be initialized", campaignPanel);
        assertEquals("Layout should be null", null, campaignPanel.getLayout());
    }

    @Test
    public void testDifficultyButtonsExist() {
        assertNotNull("Easy button should exist", campaignPanel.easyButton);
        assertNotNull("Normal button should exist", campaignPanel.normalButton);
        assertNotNull("Hard button should exist", campaignPanel.hardButton);
    }

    @Test
    public void testBackgroundImage() {
        assertTrue("Background image should be loaded", 
            campaignPanel.getClass().getResource("/Menu/menu_background.png") != null);
    }

    @Test
    public void testDrawBorderedTextBox() {
        Rectangle testLocation = new Rectangle(100, 100, 200, 50);
        campaignPanel.drawBorderedTextBox("Test", testLocation, 20);
        
        // Verify components were added
        Component[] components = campaignPanel.getComponents();
        boolean foundPanel = false;
        for (Component c : components) {
            if (c instanceof JPanel) {
                JPanel panel = (JPanel) c;
                if (panel.getBounds().equals(testLocation)) {
                    foundPanel = true;
                    break;
                }
            }
        }
        assertTrue("Bordered text box should be added to panel", foundPanel);
    }

    @Test
    public void testDrawButton() {
        Rectangle testLocation = new Rectangle(100, 100, 200, 50);
        JButton button = campaignPanel.drawButton("Test", testLocation, 20);
        
        assertNotNull("Button should be created", button);
        assertEquals("Button should have correct text", "Test", button.getText());
        assertEquals("Button should have correct bounds", testLocation, button.getBounds());
    }
}