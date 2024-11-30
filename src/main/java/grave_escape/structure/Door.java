package grave_escape.structure;

import grave_escape.game.Position;

/**
 * objectives.Door class represents the door on the game.Grid that appears once all mandatory objectives are collected.
 */
public class Door {
    Position position;

    /**
     * Constructor for objectives.Door object.
     * @param position: game.Position of the objectives.Door object
     */
    public Door(Position position) {
        this.position = position;
    }

    /**
     * Method to return X coordinate of the objectives.Door object's game.Position.
     * @return: Integer representing X coordinate of objectives.Door object
     */
    public int getX(){
        return position.getX();
    }

    /**
     * Method to return Y coordinate of the objectives.Door object's game.Position.
     * @return: Integer representing Y coordinate of objectives.Door object
     */
    public int getY(){
        return position.getY();
    }
}
