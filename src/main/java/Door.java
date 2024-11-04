/**
 * Door class represents the door on the Grid that appears once all mandatory objectives are collected.
 */
public class Door {
    Position position;

    /**
     * Constructor for Door object.
     * @param position: Position of the Door object
     */
    public Door(Position position) {
        this.position = position;
    }

    /**
     * Method to return X coordinate of the Door object's Position.
     * @return: Integer representing X coordinate of Door object
     */
    public int getX(){
        return position.getX();
    }

    /**
     * Method to return Y coordinate of the Door object's Position.
     * @return: Integer representing Y coordinate of Door object
     */
    public int getY(){
        return position.getY();
    }
}
