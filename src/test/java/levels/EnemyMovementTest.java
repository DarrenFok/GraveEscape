package levels;

import grave_escape.enemy.Enemy;
import grave_escape.enemy.MovingEnemy;
import grave_escape.enemy.StationaryEnemy;
import grave_escape.game.Position;
import grave_escape.levels.Level;
import grave_escape.objectives.Objective;
import grave_escape.objectives.Wall;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyMovementTest {
    private Level level;

    public void setupOption1() {
        int rows = 3;
        int cols = 3;
        Position playerStart = new Position(3, 3);
        List<Enemy> enemies = new ArrayList<>();
        ArrayList<Objective> objectives = new ArrayList<>();
        Position doorPosition = new Position(3,3);
        List<Wall> walls = new ArrayList<>();

        /**
         * Test Grid:
         * o o o o o
         * o o o o o
         * o o x o o
         * o o o o o
         * o o o o !
         *
         * With 'x' being the enemy, '!' being door and 'o' being open cells, with the player starting at (2,2).
         * (0,0) is in the top left corner.
         */
        enemies.add(new MovingEnemy(new Position(3,3)));
        enemies.add(new StationaryEnemy(new Position(3,3)));

        level = new Level(rows, cols, playerStart, enemies, objectives, doorPosition, walls);
    }

    public void setupOption2() {
        int rows = 3;
        int cols = 3;
        Position playerStart = new Position(3, 3);
        List<Enemy> enemies = new ArrayList<>();
        ArrayList<Objective> objectives = new ArrayList<>();
        Position doorPosition = new Position(3,3);
        List<Wall> walls = new ArrayList<>();

        /**
         * Test Grid:
         * o o o o o
         * o o ? o o
         * o ? x ? o
         * o o ? o o
         * o o o o !
         *
         * With 'x' being the enemy, '!' being door, '?' being wall and 'o' being open cells, with the player starting at (2,2).
         * (0,0) is in the top left corner.
         */
        enemies.add(new MovingEnemy(new Position(3,3)));
        enemies.add(new StationaryEnemy(new Position(3,3)));
        walls.add(new Wall(new Position(3,2)));
        walls.add(new Wall(new Position(4,3)));
        walls.add(new Wall(new Position(2,3)));
        walls.add(new Wall(new Position(3,4)));

        level = new Level(rows, cols, playerStart, enemies, objectives, doorPosition, walls);
    }

    @Test
    public void testEnemyMovementPlayerWest(){
        setupOption1();
        level.getPlayer().setPosition(new Position(1,3));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(2,3), newPosition);
    }

    @Test
    public void testEnemyMovementPlayerEast(){
        setupOption1();
        level.getPlayer().setPosition(new Position(5,3));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(4,3), newPosition);
    }

    @Test
    public void testEnemyMovementPlayerNorth(){
        setupOption1();
        level.getPlayer().setPosition(new Position(3,1));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(3,2), newPosition);
    }

    @Test
    public void testEnemyMovementPlayerSouth(){
        setupOption1();
        level.getPlayer().setPosition(new Position(3,5));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(3,4), newPosition);
    }

    @Test
    public void testEnemyMovementPlayerWestWithWall(){
        setupOption2();
        level.getPlayer().setPosition(new Position(1,3));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(3,3), newPosition);
    }

    @Test
    public void testEnemyMovementPlayerEastWithWall(){
        setupOption2();
        level.getPlayer().setPosition(new Position(5,3));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(3,3), newPosition);
    }

    @Test
    public void testEnemyMovementPlayerNorthWithWall(){
        setupOption2();
        level.getPlayer().setPosition(new Position(3,1));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(3,3), newPosition);
    }

    @Test
    public void testEnemyMovementPlayerSouthWithWall(){
        setupOption2();
        level.getPlayer().setPosition(new Position(3,5));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(3,3), newPosition);
    }

    @Test
    public void testEnemyMovementWestBorder(){
        setupOption1();
        level.getEnemies().get(0).setPosition(new Position(1,3));
        level.getPlayer().setPosition(new Position(0,3));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(1,3), newPosition);
    }

    @Test
    public void testEnemyMovementEastBorder(){
        setupOption1();
        level.getEnemies().get(0).setPosition(new Position(5,3));
        level.getPlayer().setPosition(new Position(7,3));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(5,3), newPosition);
    }

    @Test
    public void testEnemyMovementNorthBorder(){
        setupOption1();
        level.getEnemies().get(0).setPosition(new Position(3,1));
        level.getPlayer().setPosition(new Position(3,0));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(3,1), newPosition);
    }

    @Test
    public void testEnemyMovementSouthBorder(){
        setupOption1();
        level.getEnemies().get(0).setPosition(new Position(3,5));
        level.getPlayer().setPosition(new Position(3,7));
        level.moveEnemies();
        Position newPosition = level.getEnemies().get(0).getPosition();
        assertEquals(new Position(3,5), newPosition);
    }
}
