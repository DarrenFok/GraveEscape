import java.util.ArrayList;
import java.util.List;

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
    }

    private static List<Enemy> createEnemies(){
        return List.of(
            new MovingEnemy(new Position(7,8)),
            new MovingEnemy(new Position(3,6)),
            new StationaryEnemy(new Position(1,1)),
            new StationaryEnemy(new Position(4,4)),
            new StationaryEnemy(new Position(8,10)),
            new StationaryEnemy(new Position(4,8))

        );
    }

    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(7,7), true, 5));

        return objectives;
    }

    private static ArrayList<Wall> createWalls(){
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Position(6,5)));
        walls.add(new Wall(new Position(7,5)));
        walls.add(new Wall(new Position(5,1)));
        walls.add(new Wall(new Position(5,1)));
        walls.add(new Wall(new Position(5,2)));
        walls.add(new Wall(new Position(5,3)));
        walls.add(new Wall(new Position(5,4)));
        walls.add(new Wall(new Position(5,5)));
        walls.add(new Wall(new Position(5,6)));
        walls.add(new Wall(new Position(3,9)));
        walls.add(new Wall(new Position(12,2)));


        return walls;
    }
}
