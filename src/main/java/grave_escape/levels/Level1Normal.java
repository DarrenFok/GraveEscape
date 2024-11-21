package grave_escape.levels;

import java.util.ArrayList;
import java.util.List;

import grave_escape.enemy.Enemy;
import grave_escape.enemy.MovingEnemy;
import grave_escape.enemy.StationaryEnemy;
import grave_escape.game.Position;
import grave_escape.objectives.Objective;
import grave_escape.objectives.Wall;

public class Level1Normal extends Level {
    public Level1Normal(){
        super(
                10,
                15,
                new Position(1,9),//hero
                createEnemies(),
                createObjectives(),
                new Position(15,1),//door
                createWalls()
        );

        this.levelName = "levels.Level 1";
        this.difficulty = "Normal";
    }

    private static List<Enemy> createEnemies(){
        return List.of(
                new MovingEnemy(new Position(14,2)),
                new MovingEnemy(new Position(1,3)),
                new StationaryEnemy(new Position(1,4)),
                new StationaryEnemy(new Position(5,8)),
                new StationaryEnemy(new Position(7,2)),
                new StationaryEnemy(new Position(9,9)),
                new StationaryEnemy(new Position(11,4)),
                new StationaryEnemy(new Position(11,6))
        );
    }

    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(5,1), true, 25));
        objectives.add(new Objective(new Position(10,9), true, 25));
        objectives.add(new Objective(new Position(11,3), true, 25));
        objectives.add(new Objective(new Position(7,1), false, 50));
        objectives.add(new Objective(new Position(7,9), false, 50));

        return objectives;
    }

    private static ArrayList<Wall> createWalls(){
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Position(6,1)));
        walls.add(new Wall(new Position(6,2)));
        walls.add(new Wall(new Position(6,3)));
        walls.add(new Wall(new Position(5,3)));
        walls.add(new Wall(new Position(4,3)));
        walls.add(new Wall(new Position(3,3)));
        walls.add(new Wall(new Position(3,4)));
        walls.add(new Wall(new Position(3,5)));
        walls.add(new Wall(new Position(3,6)));
        walls.add(new Wall(new Position(3,7)));

        walls.add(new Wall(new Position(6,9)));
        walls.add(new Wall(new Position(6,8)));
        walls.add(new Wall(new Position(6,7)));
        walls.add(new Wall(new Position(6,6)));

        walls.add(new Wall(new Position(9,2)));
        walls.add(new Wall(new Position(9,3)));

        walls.add(new Wall(new Position(8,5)));
        walls.add(new Wall(new Position(9,5)));
        walls.add(new Wall(new Position(10,5)));
        walls.add(new Wall(new Position(11,5)));
        walls.add(new Wall(new Position(12,5)));
        walls.add(new Wall(new Position(13,5)));
        walls.add(new Wall(new Position(14,5)));

        walls.add(new Wall(new Position(13,1)));
        walls.add(new Wall(new Position(13,2)));

        walls.add(new Wall(new Position(13,8)));
        walls.add(new Wall(new Position(13,7)));

        return walls;
    }
}
