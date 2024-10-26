import java.util.List;

public class Level {
    private int levelNumber;
    private List<Objective> objectives;
    private Player player;
    private List<Enemy> enemies;
    private Grid grid;
    private int bonusPoints;
    private int tickCount;
    private boolean isComplete;

    public void startLevel(){}

    public void endLevel(){}

    public void resetLevel(){}

    public void completeObjective(){}

    public void openExit(){}

    public boolean checkLevelCompletion(){
        return false;
    }

    public int calculateBonusPoints(){
        return 0;
    }
}
