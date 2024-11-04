/**
 * Wall class represents a Wall cell within the Grid that the Player and any MovingEnemy cannot walk into.
 */
public class Wall {
    Position position;

    /**
     * Constructor for Wall object.
     * @param position: Position of the Wall object
     */
    public Wall(Position position) {
        this.position = position;
    }

    /**
     * Method to return X coordinate of the Wall object's Position.
     * @return: Integer representing X coordinate of Wall object
     */
    public int getX(){
        return position.getX();
    }

    /**
     * Method to return Y coordinate of the Wall object's Position.
     * @return: Integer representing Y coordinate of Wall object
     */
    public int getY(){
        return position.getY();
    }
}
