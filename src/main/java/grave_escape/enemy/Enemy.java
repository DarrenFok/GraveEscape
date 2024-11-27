package grave_escape.enemy;

import grave_escape.game.Position;
import grave_escape.player.MovingObject;


public abstract class Enemy extends MovingObject {
    public Enemy(Position position){
        super(position);
    }
}
