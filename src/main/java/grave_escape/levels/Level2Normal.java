package grave_escape.levels;

import java.util.ArrayList;
import java.util.List;

public class Level2Normal extends Level {
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

        this.levelName = "levels.Level 2";
        this.difficulty = "Normal";
    }

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

    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(15,1), true, 25));
        objectives.add(new Objective(new Position(12,5), true, 25));
        objectives.add(new Objective(new Position(4,6), true, 25));
        objectives.add(new Objective(new Position(3,2), false, 50));
        objectives.add(new Objective(new Position(10,10), false, 50));

        return objectives;
    }

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
