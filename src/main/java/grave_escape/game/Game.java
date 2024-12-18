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
 * The {@code Game} class acts as the main controller for the game, handling the overall game flow,
 * player interactions, and game state management.
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
     * Constructs a {@code Game} object and initializes the game panel, levels, and state.
     *
     * @param cardLayout The {@code CardLayout} used for switching between panels.
     * @param mainPanel  The main {@code JPanel} containing all game components.
     * @param difficulty The difficulty level of the game.
     * @param gameMode   The selected game mode (e.g., Practice, Campaign).
     * @param level      The initial level to be played.
     */
    public Game(CardLayout cardLayout, JPanel mainPanel, Difficulty difficulty, GameMode gameMode, Level level) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.difficulty = difficulty;
        this.gameMode = gameMode;
        this.level = level;

        // Initialize levels
        initializeLevels();

        // Create game panel and add it to the main panel
        setupGamePanel();
        mainPanel.add(gamePanel, "Game");

        // Add KeyListener to the game panel
        gamePanel.addKeyListener(this);
    }

    /**
     * Sets up the {@code GamePanel} with the current level, lives, score, and moves.
     */
    private void setupGamePanel() {
        gamePanel = new GamePanel(level, lives, score, moves);
    }

    /**
     * Handles key press events to move the player and trigger game actions.
     *
     * @param e The {@code KeyEvent} triggered by user input.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver) return;
        AudioUtils.playAudio(Values.BUTTON_PRESS_SOUND);
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
            handleAfterPlayerMovement();
        }
    }

    /**
     * Handles game updates after the player moves, including enemy movement,
     * score updates, and collision detection.
     */
    public void handleAfterPlayerMovement() {
        level.moveEnemies();
        if (score > 0) score--;
        moves++;
        score += level.checkObjective();
        level.checkAndPlaceDoor();

        if (level.isDoorOpen()) {
            playerIsOnDoor = level.isOnDoor();
        }

        gamePanel.update(level, lives, score, moves);

        gameOver = level.checkCollision();
        if (gameOver) handleGameOver();

        if (playerIsOnDoor) {
            AudioUtils.playAudio(Values.DOOR_OPEN_SOUND);
            handleLevelCompletion();
            playerIsOnDoor = false;
        }
    }

    /**
     * Handles the logic when the game ends, including saving results and resetting levels.
     */
    public void handleGameOver() {
        AudioUtils.playAudio(Values.GAME_OVER_SOUND);
        if (gameMode == GameMode.PRACTICE) {
            JOptionPane.showMessageDialog(mainPanel, "Game Over");
            cardLayout.show(mainPanel, "Menu");
            gameOver = false;
        } else {
            lives--;
            if (lives == 0) {
                JOptionPane.showMessageDialog(mainPanel, "No more lives. Game Over!");
                saveResult();
            } else {
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

    /**
     * Saves the player's results and updates the leaderboard.
     */
    private void saveResult() {
        String username = JOptionPane.showInputDialog(mainPanel, "Enter your name:", "Game Over - Save Your Score", JOptionPane.PLAIN_MESSAGE);
        cardLayout.show(mainPanel, "Menu");
        gameOver = false;
        HighestResult result = HighestResult.getInstance();
        if (username == null) username = "Player";
        result.savePlayerResult(username, score);
        SwingUtilities.invokeLater(() -> {
            CampaignPanel campaignPanel = (CampaignPanel) mainPanel.getComponent(1); // Adjust index if needed
            campaignPanel.refreshLeaderboard();
        });
    }

    /**
     * Starts the game by displaying the {@code GamePanel}.
     */
    public void startGame() {
        cardLayout.show(mainPanel, "Game");
        gamePanel.requestFocusInWindow();
        gamePanel.update(level, lives, score, moves);
    }

    /**
     * Handles the completion of a level, transitioning to the next level or ending the campaign.
     */
    public void handleLevelCompletion() {
        if (gameMode == GameMode.PRACTICE) {
            JOptionPane.showMessageDialog(mainPanel, "Level complete!");
            cardLayout.show(mainPanel, "Menu");
        } else if (gameMode == GameMode.CAMPAIGN) {
            prevScore = score;
            prevMoves = moves;
            int currentIndex = levels.indexOf(level);
            if (currentIndex < levels.size() - 1) {
                level = levels.get(currentIndex + 1);
                setupGamePanel();
                mainPanel.add(gamePanel, "Game");
                gamePanel.addKeyListener(this);
                JOptionPane.showMessageDialog(gamePanel, "Level complete! Ready to continue?");
                startGame();
            } else {
                JOptionPane.showMessageDialog(mainPanel, difficulty + " Campaign mode complete! Thanks for playing!");
                saveResult();
                cardLayout.show(mainPanel, "Menu");
            }
        } else {
            throw new IllegalArgumentException("Invalid game mode");
        }
    }

    /**
     * Initializes the levels based on the selected difficulty.
     */
    public void initializeLevels() {
        if (difficulty == Difficulty.EASY) {
            levels.add(new Level2Easy());
            levels.add(new Level3Easy());
        } else if (difficulty == Difficulty.NORMAL) {
            levels.add(new Level2Normal());
            levels.add(new Level3Normal());
        } else if (difficulty == Difficulty.HARD) {
            levels.add(new Level2Hard());
            levels.add(new Level3Hard());
        } else {
            throw new IllegalArgumentException("Invalid difficulty");
        }
    }

    /**
     * Returns the list of levels in the game.
     *
     * @return A {@code List} of {@code Level} objects.
     */
    public List<Level> getLevels() {
        return levels;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
