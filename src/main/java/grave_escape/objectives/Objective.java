package grave_escape.objectives;

import grave_escape.structure.Position;

/**
 * The objectives.Objective class represents a collectable objectives.Objective within a game.
 */
public class Objective extends Position{
    private boolean isMandatory;
    private int scoreValue;

    /**
     * Constructor for the objectives.Objective class.
     * @param position: game.Position of the objectives.Objective on the grid
     * @param isMandatory: Whether collection of objectives.Objective is needed to proceed to next level
     * @param scoreValue: Score to be added to total when objectives.Objective is collected
     */
    public Objective(Position position, boolean isMandatory, int scoreValue) {
        super(position.getX(), position.getY());
        this.isMandatory = isMandatory;
        this.scoreValue = scoreValue;
    }

    /**
     * Method to return objectives.Objective's position.
     * @return Position of Objective
     */
    public Position getPosition() {
        return this;
    }

    /**
     * Method to return whether it is mandatory to collect objectives.Objective.
     * @return Boolean for whether objectives.Objective is mandatory
     */
    public boolean isMandatory() {
        return isMandatory;
    }

    /**
     * Method to return objectives.Objective's X coordinate on game.Grid.
     * @return Objective's X coordinate
     */
    public int getX(){
        return super.getX();
    }

    /**
     * Method to return objectives.Objective's Y coordinate on game.Grid.
     * @return Objective's Y coordinate
     */
    public int getY(){
        return super.getY();
    }

    /**
     * Method to return objectives.Objective's score value.
     * @return Objective's score value
     */
    public int getScoreValue(){
        return scoreValue;
    }
}
