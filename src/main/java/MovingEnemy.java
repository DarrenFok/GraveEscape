public class MovingEnemy extends Enemy {
    private int speed;

    public MovingEnemy(Position position){
        super(position);
    }

    public int getSpeed() {
        return speed;
    }
}
