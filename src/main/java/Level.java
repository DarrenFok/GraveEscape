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
    private List<Wall> walls;
//    private int bonusPoints;
//    private int tickCount;
//    private boolean isComplete;

    /**
     * Constructor for Level object.
     * @param numOfRows: Number of rows in the Level's grid (value of max Y-coordinate)
     * @param numOfCols: Number of columns in the Levels' grid (value of max X-coordinate)
     * @param playerStart: The coordinates of where the player will start on a grid
     * @param enemies: A list of enemies on the grid
     * @param doorPosition: The Position of the Door within the Level
     */
    public Level(
            int numOfRows,
            int numOfCols,
            Position playerStart,
            List<Enemy> enemies,
            ArrayList<Objective> objectives,
            Position doorPosition,
            List<Wall> walls
    ){
        // Set grid size
        grid = new Grid(numOfRows + 2, numOfCols + 2);
        player = new Player(playerStart);

        // Set initial positions of enemies
        this.enemies = enemies;

        // Set initial positions of objectives
        this.objectives = objectives;
        // Set initial amount of mandatory objectives
        mandatoryCount = countMandatory();

        this.doorPosition = doorPosition;

        this.walls = walls;
    }

    /**
     * This method moves a player in the given direction. If the player is at the border of the grid,
     * they will stay in place, rather than moving.
     * @param direction: The direction in which the player will move
     */
    public void movePlayer(Direction direction){
        switch(direction){
            case UP:
                if(player.getY() > 0 && !isWall(player.getX(), player.getY()-1)){
                    player.setPosition(new Position(player.getX(), player.getY()-1));
                }
                break;
            case DOWN:
                if(player.getY() < grid.getNumOfRows()-1 && !isWall(player.getX(), player.getY()+1)){
                    player.setPosition(new Position(player.getX(), player.getY()+1));
                }
                break;
            case LEFT:
                if(player.getX() > 0 && !isWall(player.getX()-1, player.getY())){
                    player.setPosition(new Position(player.getX()-1, player.getY()));
                }
                break;
            case RIGHT:
                if(player.getX() < grid.getNumOfCols()-1 && !isWall(player.getX()+1, player.getY())){
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
                        if(enemy.getY() > 0 && !isWall(enemy.getX(), enemy.getY()-1)){
                            enemy.setPosition(new Position(enemy.getX(), enemy.getY()-1));
                        }
                        break;
                    case 1: // Moving down
                        if(enemy.getY() < grid.getNumOfRows()-1 && !isWall(enemy.getX(), enemy.getY()+1)){
                            enemy.setPosition(new Position(enemy.getX(), enemy.getY()+1));
                        }
                        break;
                    case 2: // Moving left
                        if(enemy.getX() > 0 && !isWall(enemy.getX()-1, enemy.getY())){
                            enemy.setPosition(new Position(enemy.getX()-1, enemy.getY()));
                        }
                        break;
                    case 3:
                        if(enemy.getX() < grid.getNumOfCols()-1 && !isWall(enemy.getX()+1, enemy.getY())){
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
     * Checks whether the door is open.
     * @return: Boolean value representing whether door is open
     */
    public boolean isDoorOpen(){
        return isDoorOpen;
    }

    /**
     * Checks whether the given coordinates are a Wall.
     * @param x: The x coordinate to be checked
     * @param y: THe y coordinate to be checked
     * @return: Boolean value representing whether a coordinate is a Wall
     */
    public boolean isWall(int x, int y){
        for(Wall wall: walls){
            if(wall.getX() == x && wall.getY() == y){
                return true;
            }
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
     * Method to return number of rows within the Level's Grid
     * @return: Integer value representing number of rows
     */
    public int getNumOfRows(){
        return grid.getNumOfRows();
    }

    /**
     * Method to return number of columns within the Level's Grid
     * @return: Integer value representing number of columns
     */
    public int getNumOfCols(){
        return grid.getNumOfCols();
    }

    /**
     * Method to return Door object within a level, namely for the Door's position.
     * @return: Door object
     */
    public Door getDoor(){
        return this.door;
    }

    /**
     * Method to return List of Walls within a level, namely for the Wall positions.
     * @return: List of Walls
     */
    public List<Wall> getWalls(){
        //Add perimeter walls
        for(int i = 0; i < getNumOfCols(); i++){
            walls.add(new Wall(new Position(i, 0)));
            walls.add(new Wall(new Position(i, getNumOfRows()-1)));
        }
        for(int j = 0; j < getNumOfRows(); j++){
            walls.add(new Wall(new Position(0, j)));
            walls.add(new Wall(new Position(getNumOfCols()-1, j)));
        }
        return this.walls;
    }
}

