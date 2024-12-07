package grave_escape.enemy;

import grave_escape.game.MovingObject;
import grave_escape.structure.Position;

/**
 * Represents an abstract enemy in the game. 
 * All enemy types inherit from this class and define specific behaviors.
 * This class extends {@link MovingObject} to inherit position and movement functionality.
 */
public abstract class Enemy extends MovingObject {

    /**
     * Constructs an {@code Enemy} object at the specified position.
     *
     * @param position The initial position of the enemy on the game grid.
     */
    public Enemy(Position position) {
        super(position);
    }
}
