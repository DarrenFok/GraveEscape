import java.util.List;

public class Level1 extends Level {
    public Level1(){
        super(
                10,
                new Position(5,4),
                List.of(
                        new MovingEnemy(new Position(0,0)),
                        new MovingEnemy(new Position(9,9)),
                        new StationaryEnemy(new Position(6,7))
                        ));
    }
}
