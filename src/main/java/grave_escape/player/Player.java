package grave_escape.player;

import grave_escape.game.Direction;
import grave_escape.game.MovingObject;
import grave_escape.structure.Position;
import grave_escape.structure.PositionUtils;
import grave_escape.structure.Wall;

import java.util.List;

public class Player extends MovingObject {

    public Player(Position position){
        super(position);
    }

    public void move(Direction direction, int gridRows, int gridCols, List<Wall> walls){
        if(direction == null){
            throw new IllegalArgumentException("Direction cannot be null");
        }
        switch(direction){
            case UP:
                if(this.getY() > 1 && !PositionUtils.isWall(this.getX(), this.getY()-1, walls)){
                    this.setPosition(new Position(this.getX(), this.getY()-1));
                }
                break;
            case DOWN:
                if(this.getY() < gridRows-2 && !PositionUtils.isWall(this.getX(), this.getY()+1, walls)){
                    this.setPosition(new Position(this.getX(), this.getY()+1));
                }
                break;
            case LEFT:
                if(this.getX() > 1 && !PositionUtils.isWall(this.getX()-1, this.getY(), walls)){
                    this.setPosition(new Position(this.getX()-1, this.getY()));
                }
                break;
            case RIGHT:
                if(this.getX() < gridCols-2 && !PositionUtils.isWall(this.getX()+1, this.getY(), walls)){
                    this.setPosition(new Position(this.getX()+1, this.getY()));
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }
}
