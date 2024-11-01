import javax.swing.*;
import java.awt.*;

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

    public PracticePanel(CardLayout cardLayout, JPanel mainPanel){
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(null);
        drawBorderedTextBox("Practice", new Rectangle(100, 50, 200, 50), 25);

        drawDifficulties();
        drawLevelSelector();

        JButton returnButton = drawButton("Return to Main Menu", new Rectangle(50, 600, 200, 50), 15);
        returnButton.addActionListener(e -> showPanel("Menu"));
    }

    /**
     * Method that draws bordered text boxes
     * @param text: The text to be added
     * @param location: Location on the panel which text box will go
     * @param fontSize: Font size of text
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

    public JButton drawButton(String text, Rectangle location, int fontSize){
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setBounds(location);
        add(button);
        return button;
    }

    public void drawDifficulties(){
        // Draw text
        JLabel diffcultyTitle = new JLabel("Difficulty Selection");
        diffcultyTitle.setFont(new Font("Arial", Font.BOLD, 20));
        diffcultyTitle.setBounds(50, 125, 300, 50);
        add(diffcultyTitle, BorderLayout.CENTER);
        // Draw border
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setLayout(null);
        difficultyPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        difficultyPanel.setBounds(50, 175, 300, 300);
        // Draw difficulty buttons
        easyButton = drawButton("Easy", new Rectangle(50, 50, 200, 50), 20);
        difficultyPanel.add(easyButton);
        easyButton.addActionListener(e -> {
            difficulty = Difficulty.EASY;
            oneButton.setText("Level One - " + difficulty.name());
            twoButton.setText("Level Two - " + difficulty.name());
            threeButton.setText("Level Three - " + difficulty.name());
        });

        normalButton = drawButton("Normal", new Rectangle(50, 125, 200, 50), 20);
        difficultyPanel.add(normalButton);
        normalButton.addActionListener(e -> {
            difficulty = Difficulty.NORMAL;
            oneButton.setText("Level One - " + difficulty.name());
            twoButton.setText("Level Two - " + difficulty.name());
            threeButton.setText("Level Three - " + difficulty.name());
        });

        hardButton = drawButton("Hard", new Rectangle(50, 200, 200, 50), 20);
        difficultyPanel.add(hardButton);
        hardButton.addActionListener(e -> {
            difficulty = Difficulty.HARD;
            oneButton.setText("Level One - " + difficulty.name());
            twoButton.setText("Level Two - " + difficulty.name());
            threeButton.setText("Level Three - " + difficulty.name());
        });

        add(difficultyPanel);
    }

    public void drawLevelSelector(){
        // Draw text
        JLabel selectorTitle = new JLabel("Pick Your Level:");
        selectorTitle.setFont(new Font("Arial", Font.BOLD, 30));
        selectorTitle.setBounds(530, 125, 700, 50);
        add(selectorTitle, BorderLayout.CENTER);

        // Draw border
        JPanel selectorPanel = new JPanel();
        selectorPanel.setLayout(null);
        selectorPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        selectorPanel.setBounds(530, 175, 700, 300);

        // Add level buttons
        oneButton = drawButton("Level One - " + difficulty.name(), new Rectangle(50, 50, 600, 50), 20);
        selectorPanel.add(oneButton);

        twoButton = drawButton("Level Two - " + difficulty.name(), new Rectangle(50, 125, 600, 50), 20);
        selectorPanel.add(twoButton);

        threeButton = drawButton("Level Three - " + difficulty.name(), new Rectangle(50, 200, 600, 50), 20);
        selectorPanel.add(threeButton);

        add(selectorPanel);
    }

    private void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
}