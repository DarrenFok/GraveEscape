package grave_escape.enemy;

import grave_escape.game.Grid;
import grave_escape.player.Player;
import grave_escape.structure.Position;
import grave_escape.structure.PositionUtils;
import grave_escape.structure.Wall;

import java.util.List;

public class MovingEnemy extends Enemy {

    public MovingEnemy(Position position){
        super(position);
    }

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
