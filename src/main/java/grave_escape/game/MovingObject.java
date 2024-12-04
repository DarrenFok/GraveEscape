package grave_escape.game;

import grave_escape.structure.Position;

public class MovingObject {
    protected Position position;
    protected Direction facing;
    private int matchPrevMove;
    public MovingObject(Position position) {
        this.position = position;
        this.facing = Direction.LEFT;
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
