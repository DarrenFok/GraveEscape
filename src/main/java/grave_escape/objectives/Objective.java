package grave_escape.objectives;

import grave_escape.structure.Position;

/**
 * The objectives.Objective class represents a collectable objectives.Objective within a game.
 */
public class Objective {
    private Position position;
    private boolean isMandatory;
    private int scoreValue;

    /**
     * Constructor for the objectives.Objective class.
     * @param position: game.Position of the objectives.Objective on the grid
     * @param isMandatory: Whether collection of objectives.Objective is needed to proceed to next level
     * @param scoreValue: Score to be added to total when objectives.Objective is collected
     */
    public Objective(Position position, boolean isMandatory, int scoreValue) {
        this.position = position;
        this.isMandatory = isMandatory;
        this.scoreValue = scoreValue;
    }

    /**
     * Method to return objectives.Objective's position.
     * @return: game.Position of objectives.Objective
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Method to return whether it is mandatory to collect objectives.Objective.
     * @return: Boolean for whether objectives.Objective is mandatory
     */
    public boolean isMandatory() {
        return isMandatory;
    }

    /**
     * Method to return objectives.Objective's X coordinate on game.Grid.
     * @return: objectives.Objective's X coordinate
     */
    public int getX(){
        return position.getX();
    }

    /**
     * Method to return objectives.Objective's Y coordinate on game.Grid.
     * @return: objectives.Objective's Y coordinate
     */
    public int getY(){
        return position.getY();
    }

    /**
     * Method to return objectives.Objective's score value.
     * @return: objectives.Objective's score value
     */
    public int getScoreValue(){
        return scoreValue;
    }
}
