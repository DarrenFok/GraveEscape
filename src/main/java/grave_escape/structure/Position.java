package grave_escape.structure;

/**
 * Represents a 2D position in a coordinate system.
 * This class encapsulates the x and y coordinates of a position
 * and provides methods for accessing and modifying these coordinates.
 */
public class Position {
    /**
     * The x-coordinate of the position.
     */
    private int x;
    /**
     * The y-coordinate of the position.
     */
    private int y;

    /**
     * Constructs a new Position object with the specified coordinates.
     *
     * @param x the x-coordinate of the position
     * @param y the y-coordinate of the position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of this position.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of this position.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of this position.
     *
     * @param x the new x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this position.
     *
     * @param y the new y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }


    @Override
    /**
     * Compares this position to the specified object for equality.
     * Two Position objects are considered equal if their
     * x and y coordinates are the same.
     *
     * @param o the object to compare
     * @return true if the specified object is equal to this position;
     *         false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position other = (Position) o;
        return this.getX() == other.getX() && this.getY() == other.getY();
    }
}
