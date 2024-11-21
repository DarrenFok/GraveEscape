package grave_escape.levels;

import java.util.ArrayList;
import java.util.List;

import grave_escape.enemy.Enemy;
import grave_escape.enemy.MovingEnemy;
import grave_escape.game.Direction;
import grave_escape.game.Grid;
import grave_escape.game.Position;
import grave_escape.objectives.Door;
import grave_escape.objectives.Objective;
import grave_escape.objectives.Wall;
import grave_escape.player.Player;


/**
 * levels.Level class represents a template for a level.
 * It manages the elements and their interactions between one another.
 */
public class Level {
    protected String levelName;
    protected String difficulty;
    private ArrayList<Objective> objectives;
    private Player player;
    private List<Enemy> enemies;
    private Grid grid;
    private Door door;
    private Position doorPosition;
    private int mandatoryCount = 0;
    private boolean isDoorOpen = false;
    private List<Wall> walls;

    private Position initialPlayerPosition;
    private List<Position> initialEnemyPositions;
    private List<Objective> initialObjectives;
    private int initialMandatoryCount;

    /**
     * Constructor for levels.Level object.
     * @param numOfRows: Number of rows in the levels.Level's grid (value of max Y-coordinate)
     * @param numOfCols: Number of columns in the Levels' grid (value of max X-coordinate)
     * @param playerStart: The coordinates of where the player will start on a grid
     * @param enemies: A list of enemies on the grid
     * @param doorPosition: The game.Position of the objectives.Door within the levels.Level
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
        initialMandatoryCount = mandatoryCount;

        this.doorPosition = doorPosition;

        this.walls = walls;

        //Store initial states
        this.initialPlayerPosition = new Position(playerStart.getX(), playerStart.getY());
        this.initialEnemyPositions = new ArrayList<>();
        for(Enemy enemy : enemies){
            initialEnemyPositions.add(new Position(enemy.getX(), enemy.getY()));
        }
        this.initialObjectives = new ArrayList<>();
        for(Objective objective : objectives){
            initialObjectives.add(
                    new Objective(
                            new Position(objective.getX(), objective.getY()),
                            objective.isMandatory(),
                            objective.getScoreValue()
                    )
            );
        }
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
    public void moveEnemies() {
        for (Enemy enemy : enemies) {
            if (enemy instanceof MovingEnemy) {
                MovingEnemy movingEnemy = (MovingEnemy) enemy;
    
                // Calculate the difference between the enemy's position and the player's position
                int deltaX = player.getX() - movingEnemy.getX();
                int deltaY = player.getY() - movingEnemy.getY();
    
                // Determine the direction the enemy should move
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    // Move horizontally first
                    if (deltaX > 0) {
                        // Move right
                        if (movingEnemy.getX() < grid.getNumOfCols() - 1 && !isWall(movingEnemy.getX() + 1, movingEnemy.getY())) {
                            movingEnemy.setPosition(new Position(movingEnemy.getX() + 1, movingEnemy.getY()));
                        }
                    } else {
                        // Move left
                        if (movingEnemy.getX() > 0 && !isWall(movingEnemy.getX() - 1, movingEnemy.getY())) {
                            movingEnemy.setPosition(new Position(movingEnemy.getX() - 1, movingEnemy.getY()));
                        }
                    }
                } else {
                    // Move vertically
                    if (deltaY > 0) {
                        // Move down
                        if (movingEnemy.getY() < grid.getNumOfRows() - 1 && !isWall(movingEnemy.getX(), movingEnemy.getY() + 1)) {
                            movingEnemy.setPosition(new Position(movingEnemy.getX(), movingEnemy.getY() + 1));
                        }
                    } else {
                        // Move up
                        if (movingEnemy.getY() > 0 && !isWall(movingEnemy.getX(), movingEnemy.getY() - 1)) {
                            movingEnemy.setPosition(new Position(movingEnemy.getX(), movingEnemy.getY() - 1));
                        }
                    }
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
     * Checks whether a player is on the same position as an objectives.Objective. If it is, it will remove it from the grid and
     * return its score. Furthermore, if the last mandatory objective is collected, it will open the door immediately.
     * @return: The score of the objective collected
     */
    public int checkObjective(){
        for(int i = 0; i < objectives.size(); i++){
            Objective objective = objectives.get(i);

            // Check if player's position matches the current objectives.Objective in list's position
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
     * Function to remove an objective from the game.Grid.
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
     * Checks whether the player is on the same position as the objectives.Door.
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
     * Checks whether the given coordinates are a objectives.Wall.
     * @param x: The x coordinate to be checked
     * @param y: THe y coordinate to be checked
     * @return: Boolean value representing whether a coordinate is a objectives.Wall
     */
    public boolean isWall(int x, int y){
        for(Wall wall: walls){
            if(wall.getX() == x && wall.getY() == y){
                return true;
            }
        }
        return false;
    }

    public void resetLevel(){
        // Reset player position
        player.setPosition(new Position(initialPlayerPosition.getX(), initialPlayerPosition.getY()));

        // Reset enemy positions
        for(int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            Position initialPosition = initialEnemyPositions.get(i);
            enemy.setPosition(new Position(initialPosition.getX(), initialPosition.getY()));
        }

        // Reset objective positions
        objectives.clear();
        for(Objective objective: initialObjectives){
            objectives.add(
                    new Objective(
                            new Position(objective.getX(), objective.getY()),
                            objective.isMandatory(),
                            objective.getScoreValue()
                    ));
        }

        // Reset door
        door = null;
        isDoorOpen = false;
        mandatoryCount = initialMandatoryCount;
    }

    /**
     * Method to return player.Player object within a level, namely for the player.Player's position.
     * @return: player.Player object
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * Method to return List of enemies within a level, namely for the enemy.Enemy positions.
     * @return: List of enemies
     */
    public List<Enemy> getEnemies(){
        return this.enemies;
    }

    /**
     * Method to return List of Objectives within a level, namely for the objectives.Objective positions.
     * @return: List of objectives
     */
    public ArrayList<Objective> getObjectives(){
        return this.objectives;
    }

    /**
     * Method to return number of rows within the levels.Level's game.Grid
     * @return: Integer value representing number of rows
     */
    public int getNumOfRows(){
        return grid.getNumOfRows();
    }

    /**
     * Method to return number of columns within the levels.Level's game.Grid
     * @return: Integer value representing number of columns
     */
    public int getNumOfCols(){
        return grid.getNumOfCols();
    }

    /**
     * Method to return objectives.Door object within a level
     * @return: objectives.Door object
     */
    public Door getDoor(){
        return this.door;
    }

    /**
     * Method to return the initial door position
     * @return: game.Position with coordinates to door
     */
    public Position getDoorPosition(){
        return this.doorPosition;
    }

    /**
     * Method to return List of Walls within a level, namely for the objectives.Wall positions.
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

    public int getMandatoryCount(){
        return mandatoryCount;
    }

    public String getLevelName(){
        return levelName;
    }

    public String getDifficulty(){
        return difficulty;
    }
}

