package grave_escape.enemy;

import grave_escape.game.MovingObject;
import grave_escape.structure.Position;


public abstract class Enemy extends MovingObject {
    public Enemy(Position position){
        super(position);
    }
}
