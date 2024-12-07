package grave_escape.levels.Level3;

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
 * Represents the hard version of Level 3.
 * This class extends the Level class and initializes specific configurations
 * such as enemies, objectives, walls, starting position, and exit position
 * for hard difficulty of Level 3.
 */
public class Level3Hard extends Level {
    /**
     * Constructs the Level3Hard object with predefined settings for the level,
     * including its grid size, starting and ending positions, enemies, objectives, and walls.
     */
    public Level3Hard(){
        super(
                10,
                15,
                new Position(1,1),//hero
                createEnemies(),
                createObjectives(),
                new Position(8,10),//door
                createWalls()
        );

        this.levelName = "Level 3";
        this.difficulty = "Hard";
    }
    /**
     * Creates a list of enemies for the level.
     *
     * @return a list of Enemy objects including moving and stationary enemies.
     */
    private static List<Enemy> createEnemies(){
        return List.of(
                new MovingEnemy(new Position(8,8)),
                new MovingEnemy(new Position(15,1)),
                new MovingEnemy(new Position(4,5)),

                new StationaryEnemy(new Position(1,2)),
                new StationaryEnemy(new Position(2,4)),
                new StationaryEnemy(new Position(2,5)),
                new StationaryEnemy(new Position(2,6)),

                new StationaryEnemy(new Position(4,4)),
                new StationaryEnemy(new Position(5,2)),
                new StationaryEnemy(new Position(5,6)),
                new StationaryEnemy(new Position(5,7)),
                new StationaryEnemy(new Position(5,9)),
                new StationaryEnemy(new Position(5,10)),

                new StationaryEnemy(new Position(8,3)),

                new StationaryEnemy(new Position(13,4)),
                new StationaryEnemy(new Position(13,5)),
                new StationaryEnemy(new Position(13,7)),

                new StationaryEnemy(new Position(10,10)),
                new StationaryEnemy(new Position(11,10))

        );
    }
    /**
     * Creates a list of objectives for the level.
     *
     * @return an ArrayList of Objective objects
     */
    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(5,1), true, MANDATORY_VALUE));
        objectives.add(new Objective(new Position(1,10), true, MANDATORY_VALUE));
        objectives.add(new Objective(new Position(10,2), true, MANDATORY_VALUE));
        objectives.add(new Objective(new Position(15,2), false, OPTIONAL_VALUE));
        objectives.add(new Objective(new Position(12,4), false, OPTIONAL_VALUE));
        objectives.add(new Objective(new Position(12,2), false, OPTIONAL_VALUE));

        return objectives;
    }
    /**
     * Creates a list of walls for the level.
     *
     * @return an ArrayList of Wall objects, defining the boundaries in the level.
     */
    private static ArrayList<Wall> createWalls(){
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Position(3,1)));
        walls.add(new Wall(new Position(3,2)));
        walls.add(new Wall(new Position(3,3)));
        walls.add(new Wall(new Position(3,4)));
        walls.add(new Wall(new Position(3,5)));
        walls.add(new Wall(new Position(3,6)));

        walls.add(new Wall(new Position(1,9)));
        walls.add(new Wall(new Position(2,9)));
        walls.add(new Wall(new Position(3,9)));

        walls.add(new Wall(new Position(6,1)));
        walls.add(new Wall(new Position(6,2)));
        walls.add(new Wall(new Position(6,3)));
        walls.add(new Wall(new Position(6,4)));
        walls.add(new Wall(new Position(6,5)));

        walls.add(new Wall(new Position(7,5)));
        walls.add(new Wall(new Position(8,5)));
        walls.add(new Wall(new Position(9,5)));
        walls.add(new Wall(new Position(10,5)));

        walls.add(new Wall(new Position(9,3)));
        walls.add(new Wall(new Position(10,3)));
        walls.add(new Wall(new Position(11,3)));
        walls.add(new Wall(new Position(12,3)));
        walls.add(new Wall(new Position(13,3)));

        walls.add(new Wall(new Position(9,2)));
        walls.add(new Wall(new Position(11,2)));
        walls.add(new Wall(new Position(13,2)));

        walls.add(new Wall(new Position(7,7)));
        walls.add(new Wall(new Position(7,8)));
        walls.add(new Wall(new Position(7,9)));
        walls.add(new Wall(new Position(7,10)));

        walls.add(new Wall(new Position(8,7)));
        walls.add(new Wall(new Position(9,7)));
        walls.add(new Wall(new Position(10,7)));

        walls.add(new Wall(new Position(10,8)));
        walls.add(new Wall(new Position(11,8)));
        walls.add(new Wall(new Position(12,8)));
        walls.add(new Wall(new Position(13,8)));

        return walls;
    }
}
