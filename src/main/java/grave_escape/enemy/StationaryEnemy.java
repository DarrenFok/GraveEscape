package grave_escape.enemy;

import grave_escape.structure.Position;

/**
 * The {@code StationaryEnemy} class represents an enemy that remains in a fixed position on the grid.
 * It extends the {@code Enemy} class, but does not have movement behavior as it does not change its position.
 */
public class StationaryEnemy extends Enemy {

    /**
     * Constructs a {@code StationaryEnemy} with the specified position.
     * @param position the position of the stationary enemy on the grid.
     */
    public StationaryEnemy(Position position) {
        super(position);
    }
}
