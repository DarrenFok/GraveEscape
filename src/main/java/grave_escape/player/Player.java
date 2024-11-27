package grave_escape.player;

import grave_escape.game.Position;

public class Player extends MovingObject {
    private int lives;
    private float fieldOfView;

    public Player(Position position){
        super(position);
    }
}
