import java.util.ArrayList;
import java.util.List;

public class Level2 extends Level {
    public Level2(){
        super(
                9,
                9,
                new Position(5,4),
                createEnemies(),
                createObjectives(),
                new Position(2,5),
                createWalls()
        );
    }

    private static List<Enemy> createEnemies(){
        return List.of(
                new StationaryEnemy(new Position(0,0))
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

        return walls;
    }
}
