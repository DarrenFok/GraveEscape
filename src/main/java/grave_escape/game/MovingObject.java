package grave_escape.game;

import grave_escape.structure.Position;

/**
 * The {@code MovingObject} class represents an object that can move on the game grid.
 * It extends the {@code Position} class, keeping track of its position and direction of movement.
 * The object also tracks whether its movement matches the previous move direction.
 */
public class MovingObject extends Position {
    /**
     * Stores the Direction in which the Object will be facing
     */
    protected Direction facing;
    private int matchPrevMove;

    /**
     * Constructs a {@code MovingObject} with the specified initial position.
     * The initial direction is set to {@code Direction.LEFT}.
     * @param position the initial position of the object on the grid.
     */
    public MovingObject(Position position) {
        super(position.getX(), position.getY());
        this.facing = Direction.LEFT;
    }

    /**
     * Gets the current position of the moving object.
     * @return the current position of the object as a {@code Position}.
     */
    public Position getPosition() {
        return this;
    }

    /**
     * Sets the position of the moving object and updates its direction based on the movement.
     * The object's facing direction is adjusted based on the relative movement direction.
     * If the direction of movement is the same as the previous movement, the {@code matchPrevMove} counter
     * is incremented, otherwise, it is decremented.
     * 
     * @param p the new position to set for the object.
     */
    public void setPosition(Position p) {
        Direction prevMove = this.facing;

        // Determine new facing direction based on movement
        if (p.getX() - this.getX() > 0) {
            this.facing = Direction.RIGHT;
        } else if (p.getX() - this.getX() < 0) {
            this.facing = Direction.LEFT;
        } else if (p.getY() - this.getY() > 0) {
            this.facing = Direction.DOWN;
        } else {
            this.facing = Direction.UP;
        }

        // Adjust matchPrevMove counter
        if (prevMove == this.facing) {
            this.matchPrevMove++;
        } else {
            this.matchPrevMove--;
        }

        // Update position
        super.setX(p.getX());
        super.setY(p.getY());
    }

    /**
     * Gets the current x-coordinate of the moving object.
     * @return the x-coordinate of the object.
     */
    public int getX() {
        return super.getX();
    }

    /**
     * Gets the current y-coordinate of the moving object.
     * @return the y-coordinate of the object.
     */
    public int getY() {
        return super.getY();
    }

    /**
     * Gets the direction the object is currently facing.
     * @return the {@code Direction} the object is facing.
     */
    public Direction getFacing() {
        return facing;
    }

    /**
     * Gets the number of consecutive moves in the same direction.
     * @return the number of moves that match the previous direction.
     */
    public int isMatchPrevMove() {
        return matchPrevMove;
    }
}
