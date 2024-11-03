/**
 * The Objective class represents a collectable Objective within a game.
 */
public class Objective {
    private Position position;
    private boolean isMandatory;
    private int scoreValue;

    /**
     * Constructor for the Objective class.
     * @param position: Position of the Objective on the grid
     * @param isMandatory: Whether collection of Objective is needed to proceed to next level
     * @param scoreValue: Score to be added to total when Objective is collected
     */
    public Objective(Position position, boolean isMandatory, int scoreValue) {
        this.position = position;
        this.isMandatory = isMandatory;
        this.scoreValue = scoreValue;
    }

    /**
     * Method to return Objective's position.
     * @return: Position of Objective
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Method to return whether it is mandatory to collect Objective.
     * @return: Boolean for whether Objective is mandatory
     */
    public boolean isMandatory() {
        return isMandatory;
    }

    /**
     * Method to return Objective's X coordinate on Grid.
     * @return: Objective's X coordinate
     */
    public int getX(){
        return position.getX();
    }

    /**
     * Method to return Objective's Y coordinate on Grid.
     * @return: Objective's Y coordinate
     */
    public int getY(){
        return position.getY();
    }

    /**
     * Method to return Objective's score value.
     * @return: Objective's score value
     */
    public int getScoreValue(){
        return scoreValue;
    }
}
