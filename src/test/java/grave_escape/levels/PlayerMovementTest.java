package grave_escape.levels;

import grave_escape.enemy.Enemy;
import grave_escape.game.Direction;
import grave_escape.structure.Position;
import grave_escape.objectives.Objective;
import grave_escape.structure.Wall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerMovementTest {
    private Level level;

    @BeforeEach
    public void Setup() {
        int rows = 3;
        int cols = 3;
        Position playerStart = new Position(2, 2);
        List<Enemy> enemies = new ArrayList<>();
        ArrayList<Objective> objectives = new ArrayList<>();
        Position doorPosition = new Position(3,3);
        List<Wall> walls = new ArrayList<>();

        /**
         * Test Grid:
         * o o x
         * o o o
         * x o !
         *
         * With 'x' being wall, '!' being door and 'o' being open cells, with the player starting at (2,2).
         * (0,0) is in the top left corner.
         */
        walls.add(new Wall(new Position(1,3)));
        walls.add(new Wall(new Position(3,1)));

        level = new Level(rows, cols, playerStart, enemies, objectives, doorPosition, walls);
    }

    @Test
    public void testMovePlayerUp(){
        level.movePlayer(Direction.UP);
        Position newPosition = level.getPlayer().getPosition();
        assertEquals(new Position(2,1), newPosition, "Player did not move up");
    }

    @Test
    public void testMovePlayerDown(){
        level.movePlayer(Direction.DOWN);
        Position newPosition = level.getPlayer().getPosition();
        assertEquals(new Position(2,3), newPosition, "Player did not move down");
    }

    @Test
    public void testMovePlayerLeft(){
        level.movePlayer(Direction.LEFT);
        Position newPosition = level.getPlayer().getPosition();
        assertEquals(new Position(1,2), newPosition, "Player did not move left");
    }

    @Test
    public void testMovePlayerRight(){
        level.movePlayer(Direction.RIGHT);
        Position newPosition = level.getPlayer().getPosition();
        assertEquals(new Position(3,2), newPosition, "Player did not move right");
    }

    @Test
    public void testMovePlayerIntoWall1(){
        // If player starts from (2,2), needs to attempt to move to (1,3). Move left, then down.
        level.movePlayer(Direction.LEFT);
        level.movePlayer(Direction.DOWN);
        Position newPosition = level.getPlayer().getPosition();
        // Position should still be (1,2)
        assertEquals(new Position(1,2), newPosition, "Player moved into the wall");
    }

    @Test
    public void testMovePlayerIntoWall2(){
        // If player starts from (2,2), needs to attempt to move to (1,3). Move down, then left.
        level.movePlayer(Direction.DOWN);
        level.movePlayer(Direction.LEFT);
        Position newPosition = level.getPlayer().getPosition();
        // Position should still be (2,3)
        assertEquals(new Position(2,3), newPosition, "Player moved into the wall");
    }

    @Test
    public void testMovePlayerIntoWall3(){
        // If player starts from (2,2), needs to attempt to move to (3,1). Move up, then right.
        level.movePlayer(Direction.UP);
        level.movePlayer(Direction.RIGHT);
        Position newPosition = level.getPlayer().getPosition();
        // Position should still be (2,1)
        assertEquals(new Position(2,1), newPosition, "Player moved into the wall");
    }

    @Test
    public void testMovePlayerIntoWall4(){
        // If player starts from (2,2), needs to attempt to move to (3,1). Move right, then up.
        level.movePlayer(Direction.RIGHT);
        level.movePlayer(Direction.UP);
        Position newPosition = level.getPlayer().getPosition();
        // Position should still be (3,2)
        assertEquals(new Position(3,2), newPosition, "Player moved into the wall");
    }

    @Test
    public void testMovePlayerIntoWestBorder(){
        // If player starts from (2,2), try to move past border of grid. Move left twice.
        level.movePlayer(Direction.LEFT);
        level.movePlayer(Direction.LEFT);
        Position newPosition = level.getPlayer().getPosition();
        assertEquals(new Position(1,2), newPosition, "Player moved into the border");
    }

    @Test
    public void testMovePlayerIntoEastBorder(){
        // If player starts from (2,2), try to move past border of grid. Move right twice.
        level.movePlayer(Direction.RIGHT);
        level.movePlayer(Direction.RIGHT);
        Position newPosition = level.getPlayer().getPosition();
        assertEquals(new Position(3,2), newPosition, "Player moved into the border");
    }

    @Test
    public void testMovePlayerIntoNorthBorder(){
        // If player starts from (2,2), try to move past border of grid. Move up twice.
        level.movePlayer(Direction.UP);
        level.movePlayer(Direction.UP);
        Position newPosition = level.getPlayer().getPosition();
        assertEquals(new Position(2,1), newPosition, "Player moved into the border");
    }

    @Test
    public void testMovePlayerIntoSouthBorder(){
        // If player starts from (2,2), try to move past border of grid. Move up twice.
        level.movePlayer(Direction.DOWN);
        level.movePlayer(Direction.DOWN);
        Position newPosition = level.getPlayer().getPosition();
        assertEquals(new Position(2,3), newPosition, "Player moved into the border");
    }

    @Test
    public void testMovePlayerNull(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> level.movePlayer(null),
                "Expected movePlayer(null) to throw IllegalArgumentException"
        );
        assertEquals("Direction cannot be null", exception.getMessage());
    }

    @Test
    public void testMovePlayerInvalidDirection(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> level.movePlayer(Direction.INVALID),
                "Expected movePlayer with invalid input to throw IllegalArgumentException"
        );
        assertEquals("Invalid direction", exception.getMessage());
    }
}
