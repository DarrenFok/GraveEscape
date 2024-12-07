package grave_escape.player;

import java.util.List;

import grave_escape.game.Direction;
import grave_escape.game.MovingObject;
import grave_escape.structure.Position;
import grave_escape.structure.PositionUtils;
import grave_escape.structure.Wall;

/**
 * The {@code Player} class represents a player in the game. It extends the {@code MovingObject} class,
 * allowing the player to move on a grid. The player can move in various directions while avoiding walls.
 */
public class Player extends MovingObject {

    /**
     * Constructs a {@code Player} object with the specified initial position.
     * @param position the initial position of the player on the grid.
     */
    public Player(Position position){
        super(position);
    }

    /**
     * Moves the player in the specified direction, provided there is no wall blocking the movement.
     * The method checks the validity of the movement and ensures the player stays within the bounds of the grid.
     * 
     * @param direction the direction to move the player.
     * @param gridRows the number of rows in the game grid.
     * @param gridCols the number of columns in the game grid.
     * @param walls a list of walls that the player must avoid while moving.
     * @throws IllegalArgumentException if the direction is {@code null} or invalid.
     */
    public void move(Direction direction, int gridRows, int gridCols, List<Wall> walls){
        if(direction == null){
            throw new IllegalArgumentException("Direction cannot be null");
        }
        switch(direction){
            case UP:
                // Move up if there is no wall and the position is within bounds
                if(this.getY() > 1 && !PositionUtils.isWall(this.getX(), this.getY()-1, walls)){
                    this.setPosition(new Position(this.getX(), this.getY()-1));
                }
                break;
            case DOWN:
                // Move down if there is no wall and the position is within bounds
                if(this.getY() < gridRows-2 && !PositionUtils.isWall(this.getX(), this.getY()+1, walls)){
                    this.setPosition(new Position(this.getX(), this.getY()+1));
                }
                break;
            case LEFT:
                // Move left if there is no wall and the position is within bounds
                if(this.getX() > 1 && !PositionUtils.isWall(this.getX()-1, this.getY(), walls)){
                    this.setPosition(new Position(this.getX()-1, this.getY()));
                }
                break;
            case RIGHT:
                // Move right if there is no wall and the position is within bounds
                if(this.getX() < gridCols-2 && !PositionUtils.isWall(this.getX()+1, this.getY(), walls)){
                    this.setPosition(new Position(this.getX()+1, this.getY()));
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }
}
