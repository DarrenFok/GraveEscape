package grave_escape.structure;

/**
 * objectives.Door class represents the door on the game.Grid that appears once all mandatory objectives are collected.
 */
public class Door extends Position{

    /**
     * Constructor for objectives.Door object.
     * @param position: game.Position of the objectives.Door object
     */
    public Door(Position position) {
        super(position.getX(), position.getY());
    }
}
