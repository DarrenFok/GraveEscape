package grave_escape.menu;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 * The {@code SettingsPanel} class is a JPanel that provides the settings screen of the game.
 * It includes a volume slider and text box to control the game music volume and a button 
 * to return to the main menu. The background of the panel is customizable with an image.
 */
public class SettingsPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JSlider volumeSlider;
    private JTextField volumeTextBox;
    private int previousVolume;
    private MusicController musicController;
    private Image backgroundImage;

    /**
     * Constructs a {@code SettingsPanel} object and initializes the user interface components.
     * Sets up the music controller, the volume slider, and a button to return to the main menu.
     * 
     * @param cardLayout the CardLayout instance used to switch between panels.
     * @param mainPanel the main panel containing all the views.
     */
    public SettingsPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        backgroundImage = new ImageIcon(getClass().getResource("/Menu/menu_background.png")).getImage();
        setLayout(null);
        musicController = MusicController.getInstance();
        musicController.playMusic(getClass().getResource("/Audio/Game.wav"));
        musicController.setVolume(70);

        // Create sound control panel
        JPanel soundPanel = new JPanel();
        soundPanel.setOpaque(false);
        soundPanel.setLayout(null);
        soundPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        soundPanel.setBounds((1280 / 2) - (300 / 2), 175, 400, 250);

        // Add sound label
        JLabel soundLabel = new JLabel("Settings");
        soundLabel.setForeground(Color.white);
        soundLabel.setFont(new Font("Arial", Font.BOLD, 30));
        soundLabel.setBounds(136, 30, 200, 30);
        soundPanel.add(soundLabel);

        JLabel volumeLabel = new JLabel("Volume: ");
        volumeLabel.setForeground(Color.white);
        volumeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        volumeLabel.setBounds(40, 120, 200, 30);
        soundPanel.add(volumeLabel);

        // Create and configure volume slider
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setOpaque(false);
        volumeSlider.setForeground(Color.white);
        volumeSlider.setBounds(100, 120, 200, 50);
        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.addChangeListener(e -> {
            int currVolume = volumeSlider.getValue();
            previousVolume = currVolume;
            volumeTextBox.setText(Integer.toString(currVolume));
            musicController.setVolume(currVolume);
        });
        soundPanel.add(volumeSlider);
        add(soundPanel);
        previousVolume = volumeSlider.getValue();

        // Create and configure volume text box
        volumeTextBox = new JTextField();
        volumeTextBox.setBounds((1280 / 2) - (300 / 2) - 200, 120, 30, 30);
        volumeTextBox.addActionListener(e -> {
            int parsedVolume = 0;
            try {
                parsedVolume = Integer.parseInt(volumeTextBox.getText());
            } catch (NumberFormatException err) {
                volumeTextBox.setText(Integer.toString(previousVolume));
                return;
            }
            previousVolume = parsedVolume;
            volumeSlider.setValue(parsedVolume);
            musicController.setVolume(parsedVolume);
        });
        soundPanel.add(volumeTextBox);

        // Add return button to return to the main menu
        JButton returnButton = drawButton("Return to Main Menu",
                new Rectangle(100, 180, 200, 50), 15);
        returnButton.addActionListener(e -> showPanel("Menu"));
        soundPanel.add(returnButton);
    }

    /**
     * Paints the background image of the panel.
     * 
     * @param g the graphics object used to paint the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Paint the panel as usual
        // Draw the background image if it is available
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Switches to the specified panel using the CardLayout.
     * 
     * @param panelName the name of the panel to switch to.
     */
    private void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    /**
     * Creates and returns a button with the specified properties.
     * 
     * @param text the text to be displayed on the button.
     * @param location the location and size of the button.
     * @param fontSize the font size of the button text.
     * @return the created {@code JButton}.
     */
    private JButton drawButton(String text, Rectangle location, int fontSize) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setBounds(location);
        add(button);
        return button;
    }
}
