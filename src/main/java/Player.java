public class Player {
    private Position position;
    private int lives;
    private float fieldOfView;

    public void setPosition(Position position) {
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

//    public Player(Position position, int lives, float fieldOfView) {}
//
//    public void move(Direction direction){}
//
//    public void collectObjective(){}
//
//    public void collectBonusPoints(){}
//
//    public void loseLife(){}
}
