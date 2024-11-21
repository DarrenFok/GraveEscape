package grave_escape.enemy;

import grave_escape.game.Position;
import grave_escape.game.Direction;


public abstract class Enemy {
    protected Position position;
    private Direction facing;
    private int matchPrevMove;

    public Enemy(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position p) {
            Direction prevMove = this.facing;

            if(p.getX() - this.position.getX() > 0) {
                this.facing = Direction.RIGHT;
            } else if(p.getX() - this.position.getX() < 0) {
                this.facing = Direction.LEFT;
            } else if(p.getY() - this.position.getY() > 0) {
                this.facing = Direction.DOWN;
            } else {
                this.facing = Direction.UP;
            }

            if(prevMove == this.facing) {
                this.matchPrevMove++;
            } else {
                this.matchPrevMove--;
            }

            this.position = p;

    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }
    
    public Direction getFacing() {
        return facing;
    }
    public int isMatchPrevMove() {
        return matchPrevMove;
    }
}
