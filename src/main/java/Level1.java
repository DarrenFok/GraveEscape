import java.util.ArrayList;
import java.util.List;

public class Level1 extends Level {
    public Level1(){
        super(
                10,
                new Position(5,4),
                createEnemies(),
                createObjectives(),
                new Position(2,5)
        );
    }

    private static List<Enemy> createEnemies(){
        return List.of(
                new MovingEnemy(new Position(0,0)),
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
}
