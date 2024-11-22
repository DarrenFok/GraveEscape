package grave_escape.levels;

import grave_escape.enemy.Enemy;
import grave_escape.enemy.MovingEnemy;
import grave_escape.game.Position;
import grave_escape.levels.Level;
import grave_escape.objectives.Objective;
import grave_escape.objectives.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResetLevelTest {
    private Level level;

    @BeforeEach
    public void setUp() {
        int rows = 5;
        int cols = 5;
        Position playerStart = new Position(2,2);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new MovingEnemy(new Position(1, 1))); // Enemy at (1, 1)
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(1, 2), true, 25)); // Mandatory objective at (1, 2)
        objectives.add(new Objective(new Position(3, 1), false, 50)); // Optional objective at (3, 1)
        Position doorPosition = new Position(4, 4);
        List<Wall> walls = new ArrayList<>();

        /**
         * Grid:
         * ! o y o o
         * x o o o o
         * o o o o o
         * o o o ? o
         * o o o o o
         *
         * '!' is enemy, 'y' is optional objective, 'x' is mandatory objective, and '?' is door. Player starts at (2,2).
         */

        level = new Level(rows, cols, playerStart, enemies, objectives, doorPosition, walls);
    }

    @Test
    public void testResetLevel() {
        level.getPlayer().setPosition(new Position(1,2));
        level.getEnemies().get(0).setPosition(new Position(2,1));
        level.checkObjective();

        // Reset level
        level.resetLevel();

        // Check that everything has been reset
        assertEquals(new Position(2,2), level.getPlayer().getPosition(), "Player position was not reset");
        assertEquals(new Position(1,1), level.getEnemies().get(0).getPosition(), "Enemy's position was not reset");
        assertEquals(2, level.getObjectives().size(), "Objectives were not reset");
        assertEquals(1, level.getMandatoryCount(), "Mandatory objectives were not reset");
        assertNull(level.getDoor(), "Door should be reset, and not placed");
        assertFalse(level.isDoorOpen(), "Door should be closed after reset");
    }
}
