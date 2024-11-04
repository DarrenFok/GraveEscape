import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Level class represents a template for a level.
 * It manages the elements and their interactions between one another.
 */
public class Level {
    private ArrayList<Objective> objectives;
    private Player player;
    private List<Enemy> enemies;
    private Grid grid;
    private Door door;
    private Position doorPosition;
    private int moveCount;
    private int mandatoryCount = 0;
    private boolean isDoorOpen = false;
//    private int bonusPoints;
//    private int tickCount;
//    private boolean isComplete;

    /**
     * Constructor for Level object.
     * @param gridSize: Size of a square shaped grid (length and width are the same)
     * @param playerStart: The coordinates of where the player will start on a grid
     * @param enemies: A list of enemies on the grid
     */
    public Level(int gridSize, Position playerStart, List<Enemy> enemies, ArrayList<Objective> objectives, Position doorPosition){
        // Set grid size
        grid = new Grid(gridSize);
        player = new Player(playerStart);

        // Set initial positions of enemies
        this.enemies = enemies;

        // Set initial positions of objectives
        this.objectives = objectives;
        // Set initial amount of mandatory objectives
        mandatoryCount = countMandatory();

        this.doorPosition = doorPosition;
    }

    /**
     * This method moves a player in the given direction. If the player is at the border of the grid,
     * they will stay in place, rather than moving.
     * @param direction: The direction in which the player will move
     */
    public void movePlayer(Direction direction){
        switch(direction){
            case UP:
                if(player.getY() > 0){
                    player.setPosition(new Position(player.getX(), player.getY()-1));
                }
                break;
            case DOWN:
                if(player.getY() < grid.getDimension()-1){
                    player.setPosition(new Position(player.getX(), player.getY()+1));
                }
                break;
            case LEFT:
                if(player.getX() > 0){
                    player.setPosition(new Position(player.getX()-1, player.getY()));
                }
                break;
            case RIGHT:
                if(player.getX() < grid.getDimension()-1){
                    player.setPosition(new Position(player.getX()+1, player.getY()));
                }
                break;
            default:
                System.out.println("Invalid direction");
        }
    }

    /**
     * Method that moves enemies each iteration of the game loop. Only movingEnemy objects are moved. If enemies are
     * at the border of the grid, they will stay in place, rather than moving.
     */
    public void moveEnemies(){
        Random random = new Random();
        for(Enemy enemy : enemies){
            if (enemy instanceof MovingEnemy){
                int direction = random.nextInt(4);
                switch(direction){
                    case 0: // Moving up
                        if(enemy.getY() > 0){
                            enemy.setPosition(new Position(enemy.getX(), enemy.getY()-1));
                        }
                        break;
                    case 1: // Moving down
                        if(enemy.getY() < grid.getDimension()-1){
                            enemy.setPosition(new Position(enemy.getX(), enemy.getY()+1));
                        }
                        break;
                    case 2: // Moving left
                        if(enemy.getX() > 0){
                            enemy.setPosition(new Position(enemy.getX()-1, enemy.getY()));
                        }
                        break;
                    case 3:
                        if(enemy.getX() < grid.getDimension()-1){
                            enemy.setPosition(new Position(enemy.getX()+1, enemy.getY()));
                        }
                        break;
                }
            }
        }
    }

    /**
     * This method checks whether a player has collided with an enemy.
     * @return: Boolean indicating whether a player is currently on the same tile as an enemy
     */
    public boolean checkCollision(){
        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).getPosition().equals(player.getPosition())){
                // Reset level
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether a player is on the same position as an Objective. If it is, it will remove it from the grid and
     * return its score. Furthermore, if the last mandatory objective is collected, it will open the door immediately.
     * @return: The score of the objective collected
     */
    public int checkObjective(){
        for(int i = 0; i < objectives.size(); i++){
            Objective objective = objectives.get(i);

            // Check if player's position matches the current Objective in list's position
            if(player.getX() == objective.getX() && player.getY() == objective.getY()){
                removeObjective(objective);
                if(objective.isMandatory()){
                    mandatoryCount--;
                }
                // Immediately check if all mandatory objectives have been collected
                checkAndPlaceDoor();

                // return score of objective
                return objective.getScoreValue();
            }
        }
        return 0;
    }

    /**
     * Function to remove an objective from the Grid.
     * @param objective: The objective to be removed
     */
    public void removeObjective(Objective objective){
        objectives.remove(objective);
    }

    /**
     * Function used to calculate how many mandatory objectives are initially in the level.
     * @return: The amount of mandatory objectives that are initially in the level
     */
    private int countMandatory(){
        for(int i = 0; i < objectives.size(); i++){
            if (objectives.get(i).isMandatory()){
                mandatoryCount++;
            }
        }
        return mandatoryCount;
    }

    /**
     * Checks whether all mandatory objectives have been placed. If they have, the door will be placed.
     */
    public void checkAndPlaceDoor(){
        if(mandatoryCount == 0 && door == null){
            door = new Door(this.doorPosition);
            isDoorOpen = true;
        }
    }

    /**
     * Checks whether the player is on the same position as the Door.
     * @return: Boolean indicating whether play is on the same position as the door
     */
    public boolean isOnDoor(){
        if(player.getX() == door.getX() && player.getY() == door.getY()){
            return true;
        }
        return false;
    }

    /**
     * Method to return Player object within a level, namely for the Player's position.
     * @return: Player object
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * Method to return List of enemies within a level, namely for the Enemy positions.
     * @return: List of enemies
     */
    public List<Enemy> getEnemies(){
        return this.enemies;
    }

    /**
     * Method to return List of Objectives within a level, namely for the Objective positions.
     * @return: List of objectives
     */
    public ArrayList<Objective> getObjectives(){
        return this.objectives;
    }

    /**
     * Method to return Dimension of the Grid.
     * @return: Integer value representing the grid's dimension
     */
    public int getDimension(){
        return grid.getDimension();
    }

    /**
     * Method to return Door object within a level, namely for the Door's position.
     * @return: Door object
     */
    public Door getDoor(){
        return this.door;
    }

    /**
     * Checks whether the door is open.
     * @return: Boolean value representing whether door is open
     */
    public boolean isDoorOpen(){
        return isDoorOpen;
    }



//    public void completeObjective(){}
//
//    public void openExit(){}
//
//    public boolean checkLevelCompletion(){
//        return false;
//    }
//
//    public int calculateBonusPoints(){
//        return 0;
//    }
}

