package grave_escape.game;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import grave_escape.levels.Difficulty;
import grave_escape.levels.GameMode;
import grave_escape.levels.Level;
import grave_escape.levels.Level2.Level2Easy;
import grave_escape.levels.Level2.Level2Hard;
import grave_escape.levels.Level2.Level2Normal;
import grave_escape.levels.Level3.Level3Easy;
import grave_escape.levels.Level3.Level3Hard;
import grave_escape.levels.Level3.Level3Normal;
import grave_escape.modes.CampaignPanel;
import grave_escape.objectives.HighestResult;

/**
 * game.Game class acts as the main controller for the game, handles overall game flow, player interactions, and
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
    private boolean playerIsOnDoor = false;
    private int lives = 3;
    private int moves = 0;
    private int prevScore = 0;
    private int prevMoves = 0;


    /**
     * Constructor for game.Game object. Sets up the game.GamePanel, switches to it, and puts the keyListener on the
     * aforementioned game.GamePanel
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

        // KeyListener in game.GamePanel
        gamePanel.addKeyListener(this);
    }

    /**
     * Method to set up the game.Game Panel
     */
    private void setupGamePanel(){
        gamePanel = new GamePanel(level, lives, score, moves);
    }

    /**
     * Method that tells game what to do when key is pressed. player.Player is moved with arrow keys, and enemies move
     * whenever the player moves.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Checks if game is over
        // If true, returns and ends function
        // otherwise, moves on
        if (gameOver) return;

        // Sets player moved state to false whenever the function runs
        // This is to set a "new turn", per se
        boolean playerMoved = false;

        switch (e.getKeyCode()) { // switch/case statement to check for directional movement
            case KeyEvent.VK_UP -> { // Checks for up key input
                level.movePlayer(Direction.UP);
                playerMoved = true;
            }
            case KeyEvent.VK_DOWN -> { // Checks for down key input
                level.movePlayer(Direction.DOWN);
                playerMoved = true;
            }
            case KeyEvent.VK_LEFT -> { // Checks for left key input
                level.movePlayer(Direction.LEFT);
                playerMoved = true;
            }
            case KeyEvent.VK_RIGHT -> { // Checks for right key input
                level.movePlayer(Direction.RIGHT);
                playerMoved = true;
            }
        }

        if (playerMoved) {
            // Originally had all of this function code inside here
            // However, it made this one function do way too much
            // Moved code down to handleAfterPlayerMovement()
            handleAfterPlayerMovement();
        }
    }

    public void handleAfterPlayerMovement() {
        // Move enemies towards the player after the player moves
        level.moveEnemies(); // Ensure this method moves the enemies towards the player

        // Update score and check objectives
        if (score > 0)
            score--;

        moves++;
        score += level.checkObjective();
        level.checkAndPlaceDoor();

        if (level.isDoorOpen()) {
            playerIsOnDoor = level.isOnDoor();
        }

        gamePanel.update(level, lives, score, moves);

        gameOver = level.checkCollision();

        if (gameOver) {
            // Handle game over logic
            handleGameOver();
        }

        if (playerIsOnDoor) {
            handleLevelCompletion();
            playerIsOnDoor = false;
        }
    }

    public void handleGameOver() {
        /*
            Checks for practice mode for game over
            This is because in practice mode, you
            technically only have one life. Once
            you lose it, it's considered a full
            game over, and you have to restart.
            This kicks you out to the main menu,
            where you'll have to re-select and
            enter practice mode.
         */
        if (gameMode == GameMode.PRACTICE) {
            JOptionPane.showMessageDialog(mainPanel, "Game Over");
            cardLayout.show(mainPanel, "Menu");
            gameOver = false;
        }
        else {  // else if campaign mode
            lives--;
            if (lives == 0) { // Checks for full game over (lives = 0)
                JOptionPane.showMessageDialog(mainPanel, "No more lives. Game Over!");
                saveResult();
            } else { // Otherwise, "soft-resets" the level, a.k.a. reverts back to the state where you entered the level.
                score = prevScore;
                moves = prevMoves;            
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
    
    private void saveResult() {
        String username = JOptionPane.showInputDialog(mainPanel,
                "Enter your name:",
                "Game Over - Save Your Score",
                JOptionPane.PLAIN_MESSAGE);
        cardLayout.show(mainPanel, "Menu");
        gameOver = false;
        HighestResult result = HighestResult.getInstance();
        if (username == null){
            username = "Player";
        }
        result.savePlayerResult(username, score);
        SwingUtilities.invokeLater(() -> {
            CampaignPanel campaignPanel = (CampaignPanel) mainPanel.getComponent(1); // Adjust index if needed
            campaignPanel.refreshLeaderboard();
        });
    }

    /**
     *
     * For both keyTyped() and keyReleased(),
     * we need to have them implemented as we implement KeyListener, and
     * these two are part of the KeyListener class and need to be implemented, or else
     * we get an error. However, they're implemented
     * as just empty functions as they don't do anything or
     * are not required for our game.
     */

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /*                                                  */

    /**
     * Starts the game by switching panels to the game.GamePanel.
     */
    public void startGame(){
        cardLayout.show(mainPanel, "Game");
        gamePanel.requestFocusInWindow();
        gamePanel.update(level, lives, score, moves);
    }

    /**
     * This function handles what level will be loaded next depending on levels.GameMode and levels.Difficulty selected in the menu.
     */
    public void handleLevelCompletion(){
        if(gameMode == GameMode.PRACTICE){
            JOptionPane.showMessageDialog(mainPanel, "Level complete!");
            cardLayout.show(mainPanel, "Menu");
        }
        else if(gameMode == GameMode.CAMPAIGN){
            // Save current level's score and moves before progressing
            prevScore = score;
            prevMoves = moves;
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
                saveResult();
                cardLayout.show(mainPanel, "Menu");
            }
        }
        else{
            throw new IllegalArgumentException("Invalid game mode");
        }
    }

    public void initializeLevels(){
        if(difficulty == Difficulty.EASY){
            levels.add(new Level2Easy());
            levels.add(new Level3Easy());
        }
        else if(difficulty == Difficulty.NORMAL){
            levels.add(new Level2Normal());
            levels.add(new Level3Normal());

        }
        else if(difficulty == Difficulty.HARD){
            levels.add(new Level2Hard());
            levels.add(new Level3Hard());
        }
        else{
            throw new IllegalArgumentException("Invalid difficulty");
        }
    }

    public List<Level> getLevels() {
        return levels;
    }

}
