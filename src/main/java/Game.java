import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Game class acts as the main controller for the game, handles overall game flow, player interactions, and
 * game state management.
 */
public class Game implements KeyListener {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<Level> levels;
    private boolean gameOver = false;
    private Difficulty difficulty;
    private GameMode gameMode;
    private Level level;
    private GamePanel gamePanel;
    private int score;

    /**
     * Constructor for Game object. Sets up the GamePanel, switches to it, and puts the keyListener on the
     * aforementioned GamePanel
     *
     * @param cardLayout: CardLayout used to switch between panels
     * @param mainPanel: The window for the game
     * @param difficulty: The difficulty of the game
     * @param gameMode: The game mode selected (practice, campaign)
     * @param level: The level being played
     */
    public Game(CardLayout cardLayout, JPanel mainPanel, Difficulty difficulty, GameMode gameMode, Level level) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.difficulty = difficulty;
        this.gameMode = gameMode;
        this.level = level;

        // Create game panel, and add to mainPanel
        setupGamePanel();
        mainPanel.add(gamePanel, "Game");

        // KeyListener in GamePanel
        gamePanel.addKeyListener(this);
    }

    /**
     * Method to set up the Game Panel
     */
    private void setupGamePanel(){
        gamePanel = new GamePanel(
                level.getNumOfRows(),
                level.getNumOfCols(),
                level.getPlayer(),
                level.getEnemies(),
                level.getObjectives()
        );
    }

    /**
     * Method that tells game what to do when key is pressed. Player is moved with arrow keys, and enemies move
     * whenever the player moves.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver) return;

        boolean playerMoved = false;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> {
                level.movePlayer(Direction.UP);
                playerMoved = true;
            }
            case KeyEvent.VK_DOWN -> {
                level.movePlayer(Direction.DOWN);
                playerMoved = true;
            }
            case KeyEvent.VK_LEFT -> {
                level.movePlayer(Direction.LEFT);
                playerMoved = true;
            }
            case KeyEvent.VK_RIGHT -> {
                level.movePlayer(Direction.RIGHT);
                playerMoved = true;
            }
        }

        if (playerMoved) {
            level.moveEnemies();
            score--;
            gamePanel.update(level.getPlayer(), level.getEnemies(), level.getObjectives());
        }

        score += level.checkObjective();
        // TODO: Temporary way to test if score is being counted. Remove when implemented in UI
        System.out.println("Score: " + score);
        gameOver = level.checkCollision();

        if (gameOver) {
            JOptionPane.showMessageDialog(mainPanel, "Game Over");
            cardLayout.show(mainPanel, "Menu");
            gameOver = false;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Starts the game by switching panels to the GamePanel.
     */
    public void startGame(){
        cardLayout.show(mainPanel, "Game");
        gamePanel.requestFocusInWindow();
    }


//    //Functions for Game
//    public void startGame(){}
//    public void endGame(){}
//    public void resetGame(){}
//    public void updateScore(){}
//    public void loseLife(){}

}
