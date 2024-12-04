package grave_escape.structure;

/**
 * objectives.Wall class represents a objectives.Wall cell within the game.Grid that the player.Player and any enemy.MovingEnemy cannot walk into.
 */
public class Wall extends Position{

    /**
     * Constructor for objectives.Wall object.
     * @param position: game.Position of the objectives.Wall object
     */
    public Wall(Position position) {
        super(position.getX(), position.getY());
    }
}
