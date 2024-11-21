import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMainMenu extends JFrame {
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel campaignPanel;
    private JPanel practicePanel;
    private JPanel settingPanel;

    private CardLayout cardLayout;

    public GameMainMenu() {
        setTitle("Grave Escape");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1366, 768);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);



        // Initialize panels
        menuPanel = initializeMenuPanel();
        campaignPanel = new CampaignPanel(cardLayout, mainPanel);
        practicePanel = new PracticePanel(cardLayout, mainPanel);
        settingPanel = new SettingsPanel(cardLayout, mainPanel);

        // Add panels to main panel
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(campaignPanel, "Campaign");
        mainPanel.add(practicePanel, "Practice");
        mainPanel.add(settingPanel, "Settings");

        // Set main pane as content pane
        setContentPane(mainPanel);
    }

    private JPanel initializeMenuPanel() {
        JPanel menuPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/Menu/menu_background.png"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        menuPanel.setLayout(null);

        JButton campaignButton = new JButton("Campaign");
        campaignButton.setBounds(540, 400, 200, 50);

        JButton practiceButton = new JButton("Practice");
        practiceButton.setBounds(540, 475, 200, 50);

        JButton settingButton = new JButton("Settings");
        settingButton.setBounds(1180, 600, 50, 50);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(540, 550, 200, 50);

        // Add action listeners to buttons
        campaignButton.addActionListener(e -> showPanel("Campaign"));
        practiceButton.addActionListener(e -> showPanel("Practice"));
        settingButton.addActionListener(e -> showPanel("Settings"));
        quitButton.addActionListener(e -> System.exit(0));

        menuPanel.add(campaignButton);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(practiceButton);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(settingButton);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(quitButton);

        return menuPanel;
    }

    private void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }


}
