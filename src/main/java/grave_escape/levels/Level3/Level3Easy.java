package grave_escape.levels.Level3;

import java.util.ArrayList;
import java.util.List;

import grave_escape.enemy.Enemy;
import grave_escape.enemy.MovingEnemy;
import grave_escape.enemy.StationaryEnemy;
import grave_escape.game.Position;
import grave_escape.levels.Level;
import grave_escape.objectives.Objective;
import grave_escape.structure.Wall;

public class Level3Easy extends Level {
    public Level3Easy(){
        super(
                10,
                15,
                new Position(13,2),
                createEnemies(),
                createObjectives(),
                new Position(3,8),
                createWalls()
        );

        this.levelName = "Level 3";
        this.difficulty = "Easy";
    }

    private static List<Enemy> createEnemies(){
        return List.of(
            new MovingEnemy(new Position(7,8)),
            new MovingEnemy(new Position(3,6)),
            new StationaryEnemy(new Position(1,1)),
            new StationaryEnemy(new Position(2,1)),
            new StationaryEnemy(new Position(3,1)),
            new StationaryEnemy(new Position(4,1)),
            new StationaryEnemy(new Position(5,1)),
            new StationaryEnemy(new Position(1,4)),
            new StationaryEnemy(new Position(5,4)),
            new StationaryEnemy(new Position(2,4)),
            new StationaryEnemy(new Position(1,2)),
            new StationaryEnemy(new Position(1,3)),
            new StationaryEnemy(new Position(5,2)),
            new StationaryEnemy(new Position(5,3)),

            new StationaryEnemy(new Position(11,3)),
            new StationaryEnemy(new Position(11,2)),
            new StationaryEnemy(new Position(11,4)),

            new StationaryEnemy(new Position(4,4)),
            new StationaryEnemy(new Position(8,10)),
            new StationaryEnemy(new Position(4,8)),
            new StationaryEnemy(new Position(2,8))


        );
    }

    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(15,10), true, 25));
        objectives.add(new Objective(new Position(7,4), true, 25));
        objectives.add(new Objective(new Position(1,10), false, 50));
        objectives.add(new Objective(new Position(3,2), false, 50));


        return objectives;
    }

    private static ArrayList<Wall> createWalls(){
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Position(7,5)));
        walls.add(new Wall(new Position(8,5)));
        walls.add(new Wall(new Position(6,1)));
        walls.add(new Wall(new Position(6,2)));
        walls.add(new Wall(new Position(6,3)));
        walls.add(new Wall(new Position(6,4)));
        walls.add(new Wall(new Position(6,5)));
        walls.add(new Wall(new Position(6,6)));
        walls.add(new Wall(new Position(3,9)));
        walls.add(new Wall(new Position(12,2)));
        walls.add(new Wall(new Position(12,3)));
        walls.add(new Wall(new Position(12,4)));

        walls.add(new Wall(new Position(13,10)));
        walls.add(new Wall(new Position(13,9)));
        walls.add(new Wall(new Position(13,8)));

        return walls;
    }
}
