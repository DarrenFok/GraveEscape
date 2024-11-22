package levels;

import grave_escape.enemy.Enemy;
import grave_escape.enemy.MovingEnemy;
import grave_escape.enemy.StationaryEnemy;
import grave_escape.game.Position;
import grave_escape.levels.Level;
import grave_escape.objectives.Objective;
import grave_escape.objectives.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckCollisionTest {
    private Level level;

    @BeforeEach
    public void setUp() {
        int rows = 3;
        int cols = 3;
        Position playerStart = new Position(2, 2);
        List<Enemy> enemies = new ArrayList<>();
        ArrayList<Objective> objectives = new ArrayList<>();
        Position doorPosition = new Position(3,3);
        List<Wall> walls = new ArrayList<>();

        /**
         * Test Grid:
         * o o o
         * y x o
         * o o !
         *
         * With 'x' being stationaryEnemy, 'y' being movingEnemy, '!' being door and 'o' being open cells,
         * with the player starting at (2,2).
         * (0,0) is in the top left corner.
         */

        enemies.add(new MovingEnemy(new Position(1,2)));
        enemies.add(new StationaryEnemy(new Position(2,2)));

        level = new Level(rows, cols, playerStart, enemies, objectives, doorPosition, walls);
    }

    @Test
    public void testCheckCollisionEmptyCell(){
        level.getPlayer().setPosition(new Position(2,1));
        boolean result = level.checkCollision();
        assertFalse(result, "Collision should not occur when player is in an empty cell away from enemies");
    }

    @Test
    public void testCheckCollisionBeforeMove(){
        boolean result = level.checkCollision();
        assertTrue(result, "Collision should occur when player is in the same position as an enemy");
    }

    @Test
    public void testCheckCollisionWithStationaryAfterMove(){
        level.getPlayer().setPosition(new Position(1,2));
        boolean result = level.checkCollision();
        assertTrue(result, "Collision should occur after player moves to an enemy's position");
    }
}
