package grave_escape.game;

import grave_escape.structure.Position;

public class MovingObject extends Position{
    protected Direction facing;
    private int matchPrevMove;

    public MovingObject(Position position) {
        super(position.getX(), position.getY());
        this.facing = Direction.LEFT;
    }

    public Position getPosition() {
        return this;
    }

    public void setPosition(Position p) {
        Direction prevMove = this.facing;

        if(p.getX() - this.getX() > 0) {
            this.facing = Direction.RIGHT;
        } else if(p.getX() - this.getX() < 0) {
            this.facing = Direction.LEFT;
        } else if(p.getY() - this.getY() > 0) {
            this.facing = Direction.DOWN;
        } else {
            this.facing = Direction.UP;
        }

        if(prevMove == this.facing) {
            this.matchPrevMove++;
        } else {
            this.matchPrevMove--;
        }

        super.setX(p.getX());
        super.setY(p.getY());

    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    public Direction getFacing() {
        return facing;
    }
    public int isMatchPrevMove() {
        return matchPrevMove;
    }
}
