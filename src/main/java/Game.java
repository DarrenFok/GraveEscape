import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Game class acts as the main controller for the game, handles overall game flow, player interactions, and
 * game state management.
 */
public class Game implements KeyListener {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<Level> levels = new ArrayList<>();
    private boolean gameOver = false;
    private Difficulty difficulty;
    private GameMode gameMode;
    private Level level;
    private GamePanel gamePanel;
    private int score;
    private boolean onDoor = false;
    private int lives = 3;

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

        // Initialize levels
        initializeLevels();

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
                level.getObjectives(),
                level.getDoor(),
                level.getWalls(),
                level.getDoorPosition()
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
            // Move enemies towards the player after the player moves
            level.moveEnemies(); // Ensure this method moves the enemies towards the player
            
            // Update score and check objectives
            score--;
            score += level.checkObjective();
            level.checkAndPlaceDoor();
            if (level.isDoorOpen()) {
                onDoor = level.isOnDoor();
            }
            gamePanel.update(level.getPlayer(), level.getEnemies(), level.getObjectives(), level.getDoor());
        }
    
        gameOver = level.checkCollision();
    
        if (gameOver) {
            // Handle game over logic
            handleGameOver();
        }
    
        if (onDoor) {
            handleLevelCompletion();
            onDoor = false;
        }
    }
    
    private void handleGameOver() {
        if (gameMode == GameMode.PRACTICE) {
            JOptionPane.showMessageDialog(mainPanel, "Game Over");
            cardLayout.show(mainPanel, "Menu");
            gameOver = false;
        } else {
            lives--;
            if (lives == 0) {
                JOptionPane.showMessageDialog(mainPanel, "No more lives. Game Over!");
                cardLayout.show(mainPanel, "Menu");
                gameOver = false;
            } else {
                score = 0;
                JOptionPane.showMessageDialog(mainPanel, lives + " lives remaining.");
                level.resetLevel();
                setupGamePanel();
                mainPanel.add(gamePanel, "Game");
                gamePanel.addKeyListener(this);
                gameOver = false;
                startGame();
            }
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
        gamePanel.update(level.getPlayer(), level.getEnemies(), level.getObjectives(), level.getDoor());
    }

    /**
     * This function handles what level will be loaded next depending on GameMode and Difficulty selected in the menu.
     */
    public void handleLevelCompletion(){
        if(gameMode == GameMode.PRACTICE){
            JOptionPane.showMessageDialog(mainPanel, "Level complete!");
            cardLayout.show(mainPanel, "Menu");
        }
        else if(gameMode == GameMode.CAMPAIGN){
            int currentIndex = levels.indexOf(level);
            if(currentIndex < levels.size()-1){
                level = levels.get(currentIndex+1);  // Loads next level
                setupGamePanel();
                mainPanel.add(gamePanel, "Game");
                gamePanel.addKeyListener(this);
                JOptionPane.showMessageDialog(gamePanel, "Level complete! Ready to continue?");
                startGame();
            }
            else{
                // No more levels, return to main menu
                JOptionPane.showMessageDialog(mainPanel, difficulty + " Campaign mode complete! Thanks for playing!");
                cardLayout.show(mainPanel, "Menu");
            }
        }
    }

    public void initializeLevels(){
        if(difficulty == Difficulty.EASY){
            levels.add(new Level2Easy());
            levels.add(new Level3Easy());
        }
        else if(difficulty == Difficulty.NORMAL){
            levels.add(new Level2Normal());
            levels.add(new Level3Hard());

        }
        else if(difficulty == Difficulty.HARD){
            levels.add(new Level2Normal());
            levels.add(new Level3Hard());
        }
    }

}
