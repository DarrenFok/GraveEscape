package grave_escape.objectives;

/**
 * objectives.Wall class represents a objectives.Wall cell within the game.Grid that the player.Player and any enemy.MovingEnemy cannot walk into.
 */
public class Wall {
    Position position;

    /**
     * Constructor for objectives.Wall object.
     * @param position: game.Position of the objectives.Wall object
     */
    public Wall(Position position) {
        this.position = position;
    }

    /**
     * Method to return X coordinate of the objectives.Wall object's game.Position.
     * @return: Integer representing X coordinate of objectives.Wall object
     */
    public int getX(){
        return position.getX();
    }

    /**
     * Method to return Y coordinate of the objectives.Wall object's game.Position.
     * @return: Integer representing Y coordinate of objectives.Wall object
     */
    public int getY(){
        return position.getY();
    }
}
