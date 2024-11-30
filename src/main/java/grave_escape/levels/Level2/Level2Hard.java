package grave_escape.levels.Level2;

import java.util.ArrayList;
import java.util.List;

import grave_escape.enemy.Enemy;
import grave_escape.enemy.MovingEnemy;
import grave_escape.enemy.StationaryEnemy;
import grave_escape.game.Position;
import grave_escape.levels.Level;
import grave_escape.objectives.Objective;
import grave_escape.structure.Wall;

public class Level2Hard extends Level {
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

    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(1,1), true, 25));
        objectives.add(new Objective(new Position(5,9), true, 25));
        objectives.add(new Objective(new Position(14,5), true, 25));
        objectives.add(new Objective(new Position(7,6), false, 50));
        objectives.add(new Objective(new Position(10,2), false, 50));
        objectives.add(new Objective(new Position(1,5), false, 50));

        return objectives;
    }

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
