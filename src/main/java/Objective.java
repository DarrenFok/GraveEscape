public class Objective {
    private Position position;
    private boolean isMandatory;

    public Objective(Position position, boolean isMandatory) {
        this.position = position;
        this.isMandatory = isMandatory;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public int getX(){
        return position.getX();
    }

    public int getY(){
        return position.getY();
    }
}
