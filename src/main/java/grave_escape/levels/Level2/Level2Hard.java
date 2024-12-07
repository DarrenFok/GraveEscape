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
 * Represents the hard version of Level 2.
 * This class extends the Level class and initializes specific configurations
 * such as enemies, objectives, walls, starting position, and exit position
 * for hard difficulty of Level 2.
 */
public class Level2Hard extends Level {
    /**
     * Constructs the Level2Hard object with predefined settings for the level,
     * including its grid size, starting and ending positions, enemies, objectives, and walls.
     */
    public Level2Hard(){
        super(
                10,
                15,
                new Position(14,1),//hero
                createEnemies(),
                createObjectives(),
                new Position(5,6),//door
                createWalls()
        );

        this.levelName = "Level 2";
        this.difficulty = "Hard";
    }
    /**
     * Creates a list of enemies for the level.
     *
     * @return a list of Enemy objects including moving and stationary enemies.
     */
    private static List<Enemy> createEnemies(){
        return List.of(
                new MovingEnemy(new Position(4,6)),
                new MovingEnemy(new Position(13,5)),
                new MovingEnemy(new Position(1,2)),
                new MovingEnemy(new Position(1,10)),
                new StationaryEnemy(new Position(2,5)),
                new StationaryEnemy(new Position(7,2)),
                new StationaryEnemy(new Position(7,4)),
                new StationaryEnemy(new Position(7,3)),
                new StationaryEnemy(new Position(10,4)),
                new StationaryEnemy(new Position(11,8)),
                new StationaryEnemy(new Position(11,9)),
                new StationaryEnemy(new Position(4,9)),
                new StationaryEnemy(new Position(14,9))
        );
    }
    /**
     * Creates a list of objectives for the level.
     *
     * @return an ArrayList of Objective objects
     */
    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(1,1), true, MANDATORY_VALUE));
        objectives.add(new Objective(new Position(5,9), true, MANDATORY_VALUE));
        objectives.add(new Objective(new Position(14,5), true, MANDATORY_VALUE));
        objectives.add(new Objective(new Position(7,6), false, OPTIONAL_VALUE));
        objectives.add(new Objective(new Position(10,2), false, OPTIONAL_VALUE));
        objectives.add(new Objective(new Position(1,5), false, OPTIONAL_VALUE));

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

        walls.add(new Wall(new Position(2,8)));
        walls.add(new Wall(new Position(2,7)));

        walls.add(new Wall(new Position(4,7)));

        walls.add(new Wall(new Position(5,7)));
        walls.add(new Wall(new Position(6,7)));
        walls.add(new Wall(new Position(7,7)));
        walls.add(new Wall(new Position(5,5)));

        walls.add(new Wall(new Position(11,1)));
        walls.add(new Wall(new Position(10,1)));
        walls.add(new Wall(new Position(9,1)));
        walls.add(new Wall(new Position(9,2)));
        walls.add(new Wall(new Position(9,3)));
        walls.add(new Wall(new Position(9,4)));

        walls.add(new Wall(new Position(14,4)));
        walls.add(new Wall(new Position(13,4)));
        walls.add(new Wall(new Position(12,4)));
        walls.add(new Wall(new Position(12,5)));
        walls.add(new Wall(new Position(12,6)));

        walls.add(new Wall(new Position(6,8)));
        walls.add(new Wall(new Position(6,9)));
        walls.add(new Wall(new Position(6,10)));

        return walls;
    }
}
