package grave_escape.menu;

import java.awt.*;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import grave_escape.modes.CampaignPanel;
import grave_escape.modes.PracticePanel;

public class GameMainMenu extends JFrame {
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel campaignPanel;
    private JPanel practicePanel;
    private JPanel settingPanel;

    private JButton campaignButton;
    private JButton practiceButton;
    private JButton settingButton;
    private JButton quitButton;

    private final CardLayout cardLayout;

    public GameMainMenu() {
        setTitle("Grave Escape");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1364, 768);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize panels
        menuPanel = initializeMenuPanel();
        campaignPanel = new CampaignPanel(cardLayout, mainPanel);
        practicePanel = new PracticePanel(cardLayout, mainPanel);
        settingPanel = new SettingsPanel(cardLayout, mainPanel);

        // Set names for panels
        menuPanel.setName("Menu");
        campaignPanel.setName("Campaign");
        practicePanel.setName("Practice");
        settingPanel.setName("Settings");

        // Add panels to main panel
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(campaignPanel, "Campaign");
        mainPanel.add(practicePanel, "Practice");
        mainPanel.add(settingPanel, "Settings");

        // Set main pane as content pane
        setContentPane(mainPanel);
    }

    JPanel initializeMenuPanel() {
        JPanel menuPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/Menu/menu_background.png"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        menuPanel.setLayout(null);

        // Draw Title
        JLabel titleLabel = new JLabel("Grave Escape");
        titleLabel.setBounds(385, 250, 500, 100);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        try{
            // Load the font from resources
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Storm Gust.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.BOLD, 72);
            titleLabel.setFont(customFont);
        }
        catch (Exception e) {
            e.printStackTrace();
            // Fall back font, in case font is missing
            titleLabel.setFont(new Font("Arial", Font.BOLD, 72));
        }
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(getWidth() / 2 - 250, 250, 500, 100);

        int buttonWidth = 200;
        int buttonHeight = 50;
        int buttonX = (getWidth() - buttonWidth) / 2; // Center buttons horizontally
        int startY = 400; // Start Y position for buttons
        int gap = 75; // Gap between buttons

        campaignButton = new JButton("Campaign");
        campaignButton.setBounds(buttonX, startY, buttonWidth, buttonHeight);

        practiceButton = new JButton("Practice");
        practiceButton.setBounds(buttonX, startY + gap, buttonWidth, buttonHeight);

        settingButton = new JButton("Settings");
        ImageIcon icon = new ImageIcon(GameMainMenu.class.getResource("/Menu/settings.png"));
        settingButton.setFont(new Font("Arial", Font.PLAIN, 0));
        settingButton.setIcon(icon);
        settingButton.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        settingButton.setBounds(1180, 600, 60, 60);

        quitButton = new JButton("Quit");
        quitButton.setBounds(buttonX, startY + gap * 2, buttonWidth, buttonHeight);

        // Add action listeners to buttons
        campaignButton.addActionListener(e -> showPanel("Campaign"));
        practiceButton.addActionListener(e -> showPanel("Practice"));
        settingButton.addActionListener(e -> showPanel("Settings"));
        quitButton.addActionListener(e -> System.exit(0));

        menuPanel.add(titleLabel);
        menuPanel.add(campaignButton);
        menuPanel.add(practiceButton);
        menuPanel.add(settingButton);
        menuPanel.add(quitButton);

        return menuPanel;
    }

    void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }


}
