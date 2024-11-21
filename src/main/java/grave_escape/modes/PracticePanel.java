package grave_escape.modes;

import grave_escape.game.Game;
import grave_escape.levels.*;

import java.awt.*;

import javax.swing.*;

/**
 * modes.PracticePanel class represents the practice section of the menu.
 * It allows the player to select a difficulty and certain level to play independently for practice.
 */
public class PracticePanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Difficulty difficulty = Difficulty.EASY;

    // Components
    JButton easyButton;
    JButton normalButton;
    JButton hardButton;

    JButton oneButton;
    JButton twoButton;
    JButton threeButton;

    private Image backgroundImage;

    /**
     * Constructor for the modes.PracticePanel class.
     * @param cardLayout: The CardLayout used for switching panels
     * @param mainPanel: The main JPanel containing all cards (panels)
     */
    public PracticePanel(CardLayout cardLayout, JPanel mainPanel){
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        backgroundImage = new ImageIcon(getClass().getResource("/Menu/menu_background.png")).getImage();

        setLayout(null);

        drawBorderedTextBox("Practice", new Rectangle(100, 50, 200, 50), 25);
        drawDifficulties();
        drawLevelSelector();

        JButton returnButton = drawButton("Return to Main Menu", new Rectangle(50, 600, 200, 50), 15);
        returnButton.addActionListener(e -> showPanel("Menu"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Paint the panel as usual
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Method that draws bordered text boxes
     * @param text: The text to be added
     * @param location: Location on the panel which text box will go
     * @param fontSize: Font size of text
     */
    public void drawBorderedTextBox(String text, Rectangle location, int fontSize){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        panel.setBounds(location);
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        label.setForeground(Color.white);
        panel.add(label, BorderLayout.CENTER);
        add(panel);
    }

    /**
     * Creates and returns a button with specified text, location, and font size.
     * @param text: The text to be displayed on the button.
     * @param location: The location and size of the button on the panel.
     * @param fontSize: The font size of the button text.
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
     * Function to draw difficulty selector title, box, and buttons respective to each difficulty.
     */
    public void drawDifficulties(){
        // Draw text
        JLabel difficultyTitle = new JLabel("levels.Difficulty Selection");
        difficultyTitle.setFont(new Font("Arial", Font.BOLD, 20));
        difficultyTitle.setForeground(Color.white);
        difficultyTitle.setBounds(50, 125, 300, 50);
        add(difficultyTitle, BorderLayout.CENTER);
        // Draw border
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setOpaque(false);
        difficultyPanel.setLayout(null);
        difficultyPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        difficultyPanel.setBounds(50, 175, 300, 300);
        // Draw difficulty buttons
        easyButton = drawButton("Easy", new Rectangle(50, 50, 200, 50), 20);
        difficultyPanel.add(easyButton);
        easyButton.addActionListener(e -> {
            difficulty = Difficulty.EASY;
            oneButton.setText("levels.Level One - " + difficulty.name());
            twoButton.setText("levels.Level Two - " + difficulty.name());
            threeButton.setText("levels.Level Three - " + difficulty.name());
        });

        normalButton = drawButton("Normal", new Rectangle(50, 125, 200, 50), 20);
        difficultyPanel.add(normalButton);
        normalButton.addActionListener(e -> {
            difficulty = Difficulty.NORMAL;
            oneButton.setText("levels.Level One - " + difficulty.name());
            twoButton.setText("levels.Level Two - " + difficulty.name());
            threeButton.setText("levels.Level Three - " + difficulty.name());
        });

        hardButton = drawButton("Hard", new Rectangle(50, 200, 200, 50), 20);
        difficultyPanel.add(hardButton);
        hardButton.addActionListener(e -> {
            difficulty = Difficulty.HARD;
            oneButton.setText("levels.Level One - " + difficulty.name());
            twoButton.setText("levels.Level Two - " + difficulty.name());
            threeButton.setText("levels.Level Three - " + difficulty.name());
        });

        add(difficultyPanel);
    }

    /**
     * Function to draw level selector title, box, and buttons respective to each level.
     */
    public void drawLevelSelector(){
        // Draw text
        JLabel selectorTitle = new JLabel("Pick Your levels.Level:");
        selectorTitle.setFont(new Font("Arial", Font.BOLD, 30));
        selectorTitle.setForeground(Color.white);
        selectorTitle.setBounds(530, 125, 700, 50);
        add(selectorTitle, BorderLayout.CENTER);

        // Draw border
        JPanel selectorPanel = new JPanel();
        selectorPanel.setOpaque(false);
        selectorPanel.setLayout(null);
        selectorPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        selectorPanel.setBounds(530, 175, 700, 300);

        // Add level buttons
        oneButton = drawButton("levels.Level One - " + difficulty.name(), new Rectangle(50, 50, 600, 50), 20);
        selectorPanel.add(oneButton);
        oneButton.addActionListener(e -> {
            Level level;
            if(difficulty == Difficulty.EASY){
                level = new Level1Easy();
            }
            else if(difficulty == Difficulty.NORMAL){
                // TODO: Replace levels.Level1Easy object with levels.Level1Normal (similar to example above in level 1 easy)
                level = new Level1Normal();
            }
            else{
                // TODO: Replace levels.Level1Easy object with levels.Level1Hard (similar to example above in level 1 easy)
                level = new Level1Hard();
            }
            Game game = new Game(cardLayout, mainPanel, difficulty, GameMode.PRACTICE, level);
            game.startGame();
        });

        twoButton = drawButton("levels.Level Two - " + difficulty.name(), new Rectangle(50, 125, 600, 50), 20);
        selectorPanel.add(twoButton);
        twoButton.addActionListener(e -> {
            // TODO: Do something similar to action in oneButton on line 135
            /* 
            levels.Level level = new Level2();
            game.Game game = new game.Game(cardLayout, mainPanel, difficulty, levels.GameMode.PRACTICE, level);
            game.startGame();*/
            Level level;
            if(difficulty == Difficulty.EASY){
                level = new Level2Easy();
            }
            else if(difficulty == Difficulty.NORMAL){
                // TODO: Replace levels.Level1Easy object with levels.Level1Normal (similar to example above in level 1 easy)
                level = new Level2Normal();
            }
            else{
                // TODO: Replace levels.Level1Easy object with levels.Level1Hard (similar to example above in level 1 easy)
                level = new Level2Hard();
            }
            Game game = new Game(cardLayout, mainPanel, difficulty, GameMode.PRACTICE, level);
            game.startGame();
        });

        threeButton = drawButton("levels.Level Three - " + difficulty.name(), new Rectangle(50, 200, 600, 50), 20);
        selectorPanel.add(threeButton);
        threeButton.addActionListener(e -> {
        // TODO: Do something similar to action in oneButton on line 135
        Level level;
        if(difficulty == Difficulty.EASY){
            level = new Level3Easy();
        }
        else if(difficulty == Difficulty.NORMAL){
            // TODO: Replace levels.Level1Easy object with levels.Level1Normal (similar to example above in level 1 easy)
            level = new Level3Normal();
        }
        else{
            // TODO: Replace levels.Level1Easy object with levels.Level1Hard (similar to example above in level 1 easy)
            level = new Level3Hard();
        }
        Game game = new Game(cardLayout, mainPanel, difficulty, GameMode.PRACTICE, level);
        game.startGame();
        });
        add(selectorPanel);
    }

    /**
     * Shows a specified panel based on the panel name provided.
     * @param panelName The name of the panel to be displayed.
     */
    private void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
}
