package grave_escape.levels;

import java.util.ArrayList;
import java.util.List;
import grave_escape.game.Position;
import grave_escape.enemy.MovingEnemy;
import grave_escape.enemy.Enemy;
import grave_escape.enemy.StationaryEnemy;
import grave_escape.objectives.Wall;
import grave_escape.objectives.Objective;

public class Level1Easy extends Level {
    public Level1Easy(){
        super(
                10,
                15,
                new Position(1,1),
                createEnemies(),
                createObjectives(),
                new Position(15,10),
                createWalls()
        );

        this.levelName = "levels.Level 1";
        this.difficulty = "Easy";
    }

    private static List<Enemy> createEnemies(){
        return List.of(
                new MovingEnemy(new Position(13,4)),
                new MovingEnemy(new Position(9,8)),
                new StationaryEnemy(new Position(1,2)),
                new StationaryEnemy(new Position(2,2)),
                new StationaryEnemy(new Position(3,2)),
                new StationaryEnemy(new Position(4,2)),
                new StationaryEnemy(new Position(5,2)),
                new StationaryEnemy(new Position(6,2)),
                new StationaryEnemy(new Position(7,2)),
                new StationaryEnemy(new Position(8,2)),
                new StationaryEnemy(new Position(9,2)),
                new StationaryEnemy(new Position(10,2)),
                new StationaryEnemy(new Position(11,2)),
                new StationaryEnemy(new Position(12,2)),
                new StationaryEnemy(new Position(13,2)),
                new StationaryEnemy(new Position(14,2)),
                new StationaryEnemy(new Position(14,6))
        );
    }

    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(14,1), true, 25));
        objectives.add(new Objective(new Position(12,6), true, 25));
        objectives.add(new Objective(new Position(13,10), true, 25));

        return objectives;
    }

    private static ArrayList<Wall> createWalls(){
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Position(1,3)));
        walls.add(new Wall(new Position(2,3)));
        walls.add(new Wall(new Position(3,3)));
        walls.add(new Wall(new Position(4,3)));
        walls.add(new Wall(new Position(5,3)));
        walls.add(new Wall(new Position(6,3)));
        walls.add(new Wall(new Position(7,3)));
        walls.add(new Wall(new Position(8,3)));
        walls.add(new Wall(new Position(9,3)));
        walls.add(new Wall(new Position(10,3)));
        walls.add(new Wall(new Position(11,3)));
        walls.add(new Wall(new Position(12,3)));
        walls.add(new Wall(new Position(13,3)));
        walls.add(new Wall(new Position(14,3)));

        walls.add(new Wall(new Position(14,4)));

        walls.add(new Wall(new Position(14,5)));
        walls.add(new Wall(new Position(1,5)));
        walls.add(new Wall(new Position(2,5)));
        walls.add(new Wall(new Position(3,5)));
        walls.add(new Wall(new Position(4,5)));
        walls.add(new Wall(new Position(5,5)));
        walls.add(new Wall(new Position(6,5)));
        walls.add(new Wall(new Position(7,5)));
        walls.add(new Wall(new Position(8,5)));
        walls.add(new Wall(new Position(9,5)));
        walls.add(new Wall(new Position(10,5)));
        walls.add(new Wall(new Position(11,5)));
        walls.add(new Wall(new Position(12,5)));
        walls.add(new Wall(new Position(13,5)));

        walls.add(new Wall(new Position(14,8)));
        walls.add(new Wall(new Position(15,8)));
        walls.add(new Wall(new Position(13,8)));
        walls.add(new Wall(new Position(12,8)));
        walls.add(new Wall(new Position(12,7)));
        walls.add(new Wall(new Position(11,7)));
        walls.add(new Wall(new Position(10,7)));
        walls.add(new Wall(new Position(9,7)));
        walls.add(new Wall(new Position(8,7)));
        walls.add(new Wall(new Position(7,7)));
        walls.add(new Wall(new Position(6,7)));
        walls.add(new Wall(new Position(5,7)));
        walls.add(new Wall(new Position(4,7)));
        walls.add(new Wall(new Position(3,7)));
        walls.add(new Wall(new Position(2,7)));

        walls.add(new Wall(new Position(14,9)));
        walls.add(new Wall(new Position(15,9)));
        walls.add(new Wall(new Position(13,9)));
        walls.add(new Wall(new Position(12,9)));
        walls.add(new Wall(new Position(11,9)));
        walls.add(new Wall(new Position(10,9)));
        walls.add(new Wall(new Position(9,9)));
        walls.add(new Wall(new Position(8,9)));
        walls.add(new Wall(new Position(7,9)));
        walls.add(new Wall(new Position(6,9)));
        walls.add(new Wall(new Position(5,9)));
        walls.add(new Wall(new Position(4,9)));
        walls.add(new Wall(new Position(3,9)));
        walls.add(new Wall(new Position(2,9)));
        walls.add(new Wall(new Position(2,8)));

        return walls;
    }
}
