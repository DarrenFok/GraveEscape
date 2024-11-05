public class Player {
    private Position position;
    private int lives;
    private float fieldOfView;
    private Direction facing;
    private int matchPrevMove;
    public Player(Position position){

        this.position = position;
        this.facing = Direction.LEFT;
        this.matchPrevMove = 0;
    }

    public void setPosition(Position position) {
        Direction prevMove = this.facing;

        if(position.getX() - this.position.getX() > 0) {
            this.facing = Direction.RIGHT;
        } else if(position.getX() - this.position.getX() < 0) {
            this.facing = Direction.LEFT;
        } else if(position.getY() - this.position.getY() > 0) {
            this.facing = Direction.DOWN;
        } else {
            this.facing = Direction.UP;
        }

        if(prevMove == this.facing) {
            this.matchPrevMove++;
        } else {
            this.matchPrevMove--;
        }

        this.position = position;

    }

    public Position getPosition() {
        return position;
    }

    public int getX(){
        return position.getX();
    }

    public int getY(){
        return position.getY();
    }

    public Direction getFacing() {
        return facing;
    }
    public int isMatchPrevMove() {
        return matchPrevMove;
    }
}
