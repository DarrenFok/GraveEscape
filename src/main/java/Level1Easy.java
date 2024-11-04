import java.util.ArrayList;
import java.util.List;

public class Level1Easy extends Level {
    public Level1Easy(){
        super(
                10,
                15,
                new Position(5,4),
                createEnemies(),
                createObjectives(),
                new Position(2,5),
                createWalls()
        );
    }

    private static List<Enemy> createEnemies(){
        return List.of(
                new MovingEnemy(new Position(1,1)),
                new MovingEnemy(new Position(9,9)),
                new StationaryEnemy(new Position(6,7))
        );
    }

    private static ArrayList<Objective> createObjectives(){
        ArrayList<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective(new Position(3,3), true, 5));
        objectives.add(new Objective(new Position(7,7), false, 10));

        return objectives;
    }

    private static ArrayList<Wall> createWalls(){
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Position(4,5)));
        walls.add(new Wall(new Position(3,5)));

        return walls;
    }
}
