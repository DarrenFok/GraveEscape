import java.util.List;

public class Game {
    private List<Level> levels;
    private Level currentLevel;
    private int livesRemaining;
    private int score;
    private Difficulty difficulty;
    private GameMode gameMode;

    public void startGame(){}
    public void endGame(){}
    public void resetGame(){}
    public void selectDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }
    public void selectGameMode(GameMode gameMode){
        this.gameMode = gameMode;
    }
    public void updateScore(){}
    public void loseLife(){}
}
