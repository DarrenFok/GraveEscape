import java.util.ArrayList;
import java.util.List;

public class Level1Hard extends Level {
    public Level1Hard(){
        super(
                10,
                15,
                new Position(10,6),//hero
                createEnemies(),
                createObjectives(),
                new Position(1,10),//door
                createWalls()
        );

        this.levelName = "Level 1";
        this.difficulty = "Hard";
    }

    private static List<Enemy> createEnemies(){
        return List.of(
                new MovingEnemy(new Position(1,8)),
                new MovingEnemy(new Position(10,1)),
                new StationaryEnemy(new Position(4,9)),
                new StationaryEnemy(new Position(1,4)),
                new StationaryEnemy(new Position(2,2)),
                new StationaryEnemy(new Position(5,5)),
                new StationaryEnemy(new Position(8,4)),
                new StationaryEnemy(new Position(9,7)),
                new StationaryEnemy(new Position(13,9)),
                new StationaryEnemy(new Position(15,9)),
                new StationaryEnemy(new Position(12,2)),
                new StationaryEnemy(new Position(7,10)),
                new StationaryEnemy(new Position(8,10)),
                new StationaryEnemy(new Position(13,2))
        );
    }

    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(14,1), true, 25));
        objectives.add(new Objective(new Position(13,10), true, 25));
        objectives.add(new Objective(new Position(1,6), true, 25));
        objectives.add(new Objective(new Position(1,1), false, 50));
        objectives.add(new Objective(new Position(5,9), false, 50));

        return objectives;
    }

    private static ArrayList<Wall> createWalls(){
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Position(1,7)));
        walls.add(new Wall(new Position(2,7)));
        walls.add(new Wall(new Position(3,7)));
        walls.add(new Wall(new Position(3,6)));
        walls.add(new Wall(new Position(3,5)));
        walls.add(new Wall(new Position(3,4)));

        walls.add(new Wall(new Position(3,2)));
        walls.add(new Wall(new Position(4,2)));
        walls.add(new Wall(new Position(5,2)));
        walls.add(new Wall(new Position(6,2)));
        walls.add(new Wall(new Position(7,2)));

        walls.add(new Wall(new Position(6,4)));
        walls.add(new Wall(new Position(6,5)));
        walls.add(new Wall(new Position(6,6)));
        walls.add(new Wall(new Position(6,7)));
        walls.add(new Wall(new Position(6,8)));
        walls.add(new Wall(new Position(7,4)));

        walls.add(new Wall(new Position(8,8)));
        walls.add(new Wall(new Position(9,8)));
        walls.add(new Wall(new Position(8,7)));

        walls.add(new Wall(new Position(12,10)));
        walls.add(new Wall(new Position(12,9)));
        walls.add(new Wall(new Position(12,8)));
        walls.add(new Wall(new Position(12,7)));
        walls.add(new Wall(new Position(13,7)));

        walls.add(new Wall(new Position(10,2)));
        walls.add(new Wall(new Position(14,3)));
        walls.add(new Wall(new Position(13,3)));
        walls.add(new Wall(new Position(12,3)));
        walls.add(new Wall(new Position(11,3)));
        walls.add(new Wall(new Position(10,3)));

        return walls;
    }
}
