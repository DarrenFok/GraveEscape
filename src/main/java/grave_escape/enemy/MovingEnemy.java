package grave_escape.enemy;

import java.util.List;

import grave_escape.game.Grid;
import grave_escape.player.Player;
import grave_escape.structure.Position;
import grave_escape.structure.PositionUtils;
import grave_escape.structure.Wall;

/**
 * The {@code MovingEnemy} class represents an enemy that moves towards the player on the game grid.
 * It extends the {@code Enemy} class and implements movement logic that adjusts the enemy's position
 * based on the player's location, avoiding walls.
 */
public class MovingEnemy extends Enemy {

    /**
     * Constructs a {@code MovingEnemy} with a specified starting position.
     * 
     * @param position the initial position of the enemy on the grid.
     */
    public MovingEnemy(Position position){
        super(position);
    }

    /**
     * Moves the enemy towards the player on the grid, considering walls.
     * The movement prioritizes horizontal movement if the player is further along the x-axis,
     * otherwise, it moves vertically.
     * The movement stops if a wall is in the way or the edge of the grid is reached.
     * 
     * @param player the player object, used to get the player's current position.
     * @param grid the grid object, used to get the grid dimensions.
     * @param walls a list of walls on the grid to check for obstacles while moving.
     */
    public void moveTowardsPlayer(Player player, Grid grid, List<Wall> walls){
        // Calculate the difference between the enemy's position and the player's position
        int deltaX = player.getX() - this.getX();
        int deltaY = player.getY() - this.getY();

        // Determine the direction the enemy should move
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            // Move horizontally first
            if (deltaX > 0) {
                // Move right
                if (this.getX() < grid.getNumOfCols() - 1 && !PositionUtils.isWall(this.getX() + 1, this.getY(), walls)) {
                    this.setPosition(new Position(this.getX() + 1, this.getY()));
                }
            } else {
                // Move left
                if (this.getX() > 1 && !PositionUtils.isWall(this.getX() - 1, this.getY(), walls)) {
                    this.setPosition(new Position(this.getX() - 1, this.getY()));
                }
            }
        } else {
            // Move vertically
            if (deltaY > 0) {
                // Move down
                if (this.getY() < grid.getNumOfRows() - 1 && !PositionUtils.isWall(this.getX(), this.getY() + 1, walls)) {
                    this.setPosition(new Position(this.getX(), this.getY() + 1));
                }
            } else {
                // Move up
                if (this.getY() > 1 && !PositionUtils.isWall(this.getX(), this.getY() - 1, walls)) {
                    this.setPosition(new Position(this.getX(), this.getY() - 1));
                }
            }
        }
    }
}
