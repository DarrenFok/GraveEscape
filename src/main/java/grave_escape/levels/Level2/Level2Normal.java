package grave_escape.levels.Level2;

import java.util.ArrayList;
import java.util.List;

import static grave_escape.game.Values.MANDATORY_VALUE;
import static grave_escape.game.Values.OPTIONAL_VALUE;
import grave_escape.enemy.Enemy;
import grave_escape.enemy.MovingEnemy;
import grave_escape.enemy.StationaryEnemy;
import grave_escape.structure.Position;
import grave_escape.levels.Level;
import grave_escape.objectives.Objective;
import grave_escape.structure.Wall;

/**
 * Represents the normal version of Level 2.
 * This class extends the Level class and initializes specific configurations
 * such as enemies, objectives, walls, starting position, and exit position
 * for normal difficulty of Level 2.
 */
public class Level2Normal extends Level {
    /**
     * Constructs the Level2Normal object with predefined settings for the level,
     * including its grid size, starting and ending positions, enemies, objectives, and walls.
     */
    public Level2Normal(){
        super(
                10,
                15,
                new Position(2,10),//hero
                createEnemies(),
                createObjectives(),
                new Position(1,1),//door
                createWalls()
        );

        this.levelName = "Level 2";
        this.difficulty = "Normal";
    }
    /**
     * Creates a list of enemies for the level.
     *
     * @return a list of Enemy objects including moving and stationary enemies.
     */
    private static List<Enemy> createEnemies(){
        return List.of(
                new MovingEnemy(new Position(4,7)),
                new MovingEnemy(new Position(11,1)),
                new StationaryEnemy(new Position(1,2)),
                new StationaryEnemy(new Position(2,2)),
                new StationaryEnemy(new Position(4,2)),

                new StationaryEnemy(new Position(7,3)),
                new StationaryEnemy(new Position(8,3)),
                new StationaryEnemy(new Position(8,4)),
                new StationaryEnemy(new Position(10,2)),
                new StationaryEnemy(new Position(10,3)),
                new StationaryEnemy(new Position(10,4)),
                new StationaryEnemy(new Position(11,9)),
                new StationaryEnemy(new Position(11,10)),
                new StationaryEnemy(new Position(13,3)),
                new StationaryEnemy(new Position(1,6)),
                new StationaryEnemy(new Position(1,7)),
                new StationaryEnemy(new Position(5,10)),
                new StationaryEnemy(new Position(6,10))






        );
    }
    /**
     * Creates a list of objectives for the level.
     *
     * @return an ArrayList of Objective objects
     */
    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(15,1), true, MANDATORY_VALUE));
        objectives.add(new Objective(new Position(12,5), true, MANDATORY_VALUE));
        objectives.add(new Objective(new Position(4,6), true, MANDATORY_VALUE));
        objectives.add(new Objective(new Position(3,2), false, OPTIONAL_VALUE));
        objectives.add(new Objective(new Position(10,10), false, OPTIONAL_VALUE));

        return objectives;
    }
    /**
     * Creates a list of walls for the level.
     *
     * @return an ArrayList of Wall objects, defining the boundaries in the level.
     */
    private static ArrayList<Wall> createWalls(){
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Position(1,3)));
        walls.add(new Wall(new Position(2,3)));
        walls.add(new Wall(new Position(3,3)));
        walls.add(new Wall(new Position(4,3)));

        walls.add(new Wall(new Position(6,2)));
        walls.add(new Wall(new Position(7,2)));
        walls.add(new Wall(new Position(8,2)));
        walls.add(new Wall(new Position(9,2)));

        walls.add(new Wall(new Position(9,2)));
        walls.add(new Wall(new Position(9,3)));
        walls.add(new Wall(new Position(9,4)));
        walls.add(new Wall(new Position(9,5)));
        walls.add(new Wall(new Position(9,6)));
        walls.add(new Wall(new Position(9,7)));
        walls.add(new Wall(new Position(9,8)));

        walls.add(new Wall(new Position(3,8)));
        walls.add(new Wall(new Position(4,8)));
        walls.add(new Wall(new Position(5,8)));
        walls.add(new Wall(new Position(6,8)));
        walls.add(new Wall(new Position(7,8)));
        walls.add(new Wall(new Position(8,8)));

        walls.add(new Wall(new Position(3,5)));
        walls.add(new Wall(new Position(3,6)));
        walls.add(new Wall(new Position(3,7)));

        walls.add(new Wall(new Position(4,5)));
        walls.add(new Wall(new Position(5,5)));
        walls.add(new Wall(new Position(6,5)));

        walls.add(new Wall(new Position(6,6)));

        walls.add(new Wall(new Position(12,1)));
        walls.add(new Wall(new Position(12,2)));
        walls.add(new Wall(new Position(12,3)));
        walls.add(new Wall(new Position(12,4)));
        walls.add(new Wall(new Position(12,6)));

        walls.add(new Wall(new Position(13,1)));
        walls.add(new Wall(new Position(13,4)));
        walls.add(new Wall(new Position(13,5)));
        walls.add(new Wall(new Position(13,6)));

        walls.add(new Wall(new Position(12,8)));
        walls.add(new Wall(new Position(13,8)));
        walls.add(new Wall(new Position(14,8)));
        walls.add(new Wall(new Position(15,8)));
        walls.add(new Wall(new Position(12,9)));
        walls.add(new Wall(new Position(13,9)));
        walls.add(new Wall(new Position(14,9)));
        walls.add(new Wall(new Position(15,9)));
        walls.add(new Wall(new Position(12,10)));
        walls.add(new Wall(new Position(13,10)));
        walls.add(new Wall(new Position(14,10)));
        walls.add(new Wall(new Position(15,10)));
        return walls;
    }
}
