import javax.swing.*;
import java.awt.*;

/**
 * CampaignPanel class represents the campaign selection menu in the game.
 * Allows the player to choose a difficulty level and displays previous high scores.
 */
public class CampaignPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Difficulty difficulty = Difficulty.EASY;

    // Components
    JButton easyButton;
    JButton normalButton;
    JButton hardButton;

    /**
     * Constructor for the CampaignPanel class.
     * @param cardLayout The CardLayout used for switching panels.
     * @param mainPanel  The main JPanel containing all cards (panels).
     */
    public CampaignPanel(CardLayout cardLayout, JPanel mainPanel){
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(null);
        drawBorderedTextBox("Campaign", new Rectangle((1280 / 2) - 100, 50, 200, 50), 25);

        drawDifficulties();
        drawPreviousHiScore();

        JButton returnButton = drawButton("Return to Main Menu", new Rectangle((1280 / 2) - (200 / 2), 600, 200, 50), 15);
        returnButton.addActionListener(e -> showPanel("Menu"));
    }

    /**
     * Draws a bordered text box with specified text, location, and font size.
     * @param text     The text to be displayed inside the text box.
     * @param location The location and size of the text box on the panel.
     * @param fontSize The font size of the text.
     */
    public void drawBorderedTextBox(String text, Rectangle location, int fontSize){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.setBounds(location);
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        panel.add(label, BorderLayout.CENTER);
        add(panel);
    }

    /**
     * Creates and returns a button with specified text, location, and font size.
     * @param text     The text to be displayed on the button.
     * @param location The location and size of the button on the panel.
     * @param fontSize The font size of the button text.
     * @return The JButton created
     */
    public JButton drawButton(String text, Rectangle location, int fontSize){
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setBounds(location);
        add(button);
        return button;
    }

    /**
     * Draws difficulty selection panels
     */
    public void drawDifficulties(){
        // Draw text
        JLabel diffcultyTitle = new JLabel("Difficulty Selection");
        diffcultyTitle.setFont(new Font("Arial", Font.BOLD, 20));
        diffcultyTitle.setBounds((1280 / 2) - (300 / 2), 125, 300, 50);
        add(diffcultyTitle, BorderLayout.CENTER);
        // Draw border
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setLayout(null);
        difficultyPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        difficultyPanel.setBounds((1280 / 2) - (300 / 2), 175, 300, 300);
        // Draw difficulty buttons
        easyButton = drawButton("Easy", new Rectangle(50, 50, 200, 50), 20);
        difficultyPanel.add(easyButton);
        easyButton.addActionListener(e -> {
            difficulty = Difficulty.EASY;
            Level level = new Level1();
            Game game = new Game(cardLayout, mainPanel, difficulty, GameMode.CAMPAIGN, level);
            game.startGame();
        });

        normalButton = drawButton("Normal", new Rectangle(50, 125, 200, 50), 20);
        difficultyPanel.add(normalButton);
        normalButton.addActionListener(e -> {
            difficulty = Difficulty.NORMAL;
            System.out.println(difficulty);
        });

        hardButton = drawButton("Hard", new Rectangle(50, 200, 200, 50), 20);
        difficultyPanel.add(hardButton);
        hardButton.addActionListener(e -> {
            difficulty = Difficulty.HARD;
            System.out.println(difficulty);
        });

        add(difficultyPanel);
    }

    /**
     * Displays previous high scores for different levels.
     */
    public void drawPreviousHiScore(){
        // Draw text
        JLabel selectorTitle = new JLabel("Previous Hi-Score");
        selectorTitle.setFont(new Font("Arial", Font.BOLD, 20));
        selectorTitle.setBounds((1280) - (700 / 2), 300, 700, 50);
        add(selectorTitle, BorderLayout.CENTER);

        // for looping the player name with score (Singleton instance for later)
    }

    /**
     * Shows a specified panel based on the panel name provided.
     * @param panelName The name of the panel to be displayed.
     */
    private void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
}