import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.IllegalFormatConversionException;

public class SettingsPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JSlider volumeSlider;
    private JTextField volumeTextBox;
    private int previousVolume;

    private Image backgroundImage;
    
    public SettingsPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        backgroundImage = new ImageIcon(getClass().getResource("/Menu/menu_background.png")).getImage();
        setLayout(null);

        // Create sound control panel
        JPanel soundPanel = new JPanel();
        soundPanel.setOpaque(false);
        soundPanel.setLayout(null);
        soundPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        soundPanel.setBounds((1280 / 2) - (300 / 2), 175, 400, 250);

        // Add sound label
        JLabel soundLabel = new JLabel("Settings");
        soundLabel.setForeground(Color.white);
        soundLabel.setFont(new Font("Arial", Font.BOLD, 20));
        soundLabel.setBounds(50, 20, 200, 30);
        soundPanel.add(soundLabel);

        JLabel volumeLabel = new JLabel("Volume: ");
        volumeLabel.setForeground(Color.white);
        volumeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        volumeLabel.setBounds(50, 120, 200, 30);
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
                });
        soundPanel.add(volumeSlider);
        add(soundPanel);
        previousVolume = volumeSlider.getValue();

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
        });
        soundPanel.add(volumeTextBox);

                // Add return button
                JButton returnButton = drawButton("Return to Main Menu",
                        new Rectangle(100, 180, 200, 50), 15);
        returnButton.addActionListener(e -> showPanel("Menu"));
        soundPanel.add(returnButton);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Paint the panel as usual
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    private JButton drawButton(String text, Rectangle location, int fontSize) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setBounds(location);
        add(button);
        return button;
    }


}
