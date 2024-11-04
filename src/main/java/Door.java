public class Door {
    Position position;

    public Door(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getX(){
        return position.getX();
    }

    public int getY(){
        return position.getY();
    }
}
