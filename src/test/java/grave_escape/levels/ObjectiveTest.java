package grave_escape.levels;

import grave_escape.enemy.Enemy;
import grave_escape.game.Position;
import grave_escape.levels.Level;
import grave_escape.objectives.Objective;
import grave_escape.objectives.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectiveTest {
    private Level level;

    @BeforeEach
    public void setUp() {
        int rows = 4;
        int cols = 4;
        Position playerStart = new Position(2, 2);
        List<Enemy> enemies = new ArrayList<>();
        ArrayList<Objective> objectives = new ArrayList<>();
        Position doorPosition = new Position(4,4);
        List<Wall> walls = new ArrayList<>();

        /**
         * Test Grid:
         * o o x o
         * y o o o
         * o o o o
         * o o o !
         *
         * With 'x' being optional objective, 'y' being mandatory objective, '!' being door and 'o' being open cells,
         * with the player starting at (2,2).
         * (0,0) is in the top left corner.
         */

        objectives.add(new Objective(new Position(1,2), true, 25)); // mandatory
        objectives.add(new Objective(new Position(3,1), false, 50)); // non-mandatory

        level = new Level(rows, cols, playerStart, enemies, objectives, doorPosition, walls);
    }

    @Test
    public void checkObjectiveEmptyCell(){
        level.getPlayer().setPosition(new Position(3,3));
        int reward = level.checkObjective();
        assertEquals(0, reward, "A reward should not have been collected");
        assertEquals(1, level.getMandatoryCount(), "MandatoryCount should not have gone down");
    }

    @Test
    public void checkObjectiveMandatoryCell(){
        level.getPlayer().setPosition(new Position(1,2));
        int reward = level.checkObjective();
        boolean doorOpen = level.isDoorOpen();
        Position expectedDoor = level.getDoorPosition();
        assertEquals(25, reward, "A reward of value 25 should have been collected");
        assertEquals(0, level.getMandatoryCount(), "MandatoryCount should have gone down to 0");
        assertTrue(doorOpen, "Door should be open");
        assertEquals(new Position(4,4), expectedDoor, "Door is not in the expected position");
        level.checkAndPlaceDoor();
    }

    @Test
    public void checkObjectiveOptionalCell(){
        level.getPlayer().setPosition(new Position(3,1));
        int reward = level.checkObjective();
        boolean doorOpen = level.isDoorOpen();
        assertEquals(50, reward, "A reward of value 50 should have been collected");
        assertEquals(1, level.getMandatoryCount(), "MandatoryCount should not have gone down");
        assertFalse(doorOpen, "Door should not be open");
    }

    @Test
    public void testIsOnDoor(){
        level.getPlayer().setPosition(new Position(1,2));
        int reward = level.checkObjective();
        boolean doorOpen = level.isDoorOpen();
        level.getPlayer().setPosition(new Position(4,4));
        boolean isOnDoor = level.isOnDoor();
        assertTrue(isOnDoor, "Player should be on same position as door");
    }

    @Test
    public void testIsOffDoor(){
        level.getPlayer().setPosition(new Position(1,2));
        int reward = level.checkObjective();
        boolean doorOpen = level.isDoorOpen();
        level.getPlayer().setPosition(new Position(4,2));
        boolean isOnDoor1 = level.isOnDoor();
        assertFalse(isOnDoor1, "Player should not be on same position as door");
        level.getPlayer().setPosition(new Position(2,4));
        boolean isOnDoor2 = level.isOnDoor();
        assertFalse(isOnDoor2, "Player should not be on same position as door");
    }


}
