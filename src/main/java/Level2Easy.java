import java.util.ArrayList;
import java.util.List;

public class Level2Easy extends Level {
    public Level2Easy(){
        super(
                10,
                15,
                new Position(12,5),
                createEnemies(),
                createObjectives(),
                new Position(3,4),
                createWalls()
        );
    }

    private static List<Enemy> createEnemies(){
        return List.of(
            new MovingEnemy(new Position(5,5)),
            new MovingEnemy(new Position(8,10)),
            new StationaryEnemy(new Position(1,1)),
            new StationaryEnemy(new Position(4,4)),
            new StationaryEnemy(new Position(8,10))
        );
    }

    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(7,7), true, 5));

        return objectives;
    }

    private static ArrayList<Wall> createWalls(){
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Position(4,5)));
        walls.add(new Wall(new Position(3,5)));
        walls.add(new Wall(new Position(10,1)));
        walls.add(new Wall(new Position(10,1)));
        walls.add(new Wall(new Position(10,2)));
        walls.add(new Wall(new Position(10,3)));
        walls.add(new Wall(new Position(10,4)));
        walls.add(new Wall(new Position(10,5)));
        walls.add(new Wall(new Position(10,6)));



        return walls;
    }
}
