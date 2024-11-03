import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Level {
    private List<Objective> objectives;
    private Player player;
    private List<Enemy> enemies;
    // TODO: remove enemyPositions, and move its functionality to enemies
    private List<Position> enemyPositions;
    private Grid grid;
    private int moveCount;
//    private int bonusPoints;
//    private int tickCount;
//    private boolean isComplete;

    /**
     * Constructor for Level object.
     * @param gridSize: Size of a square shaped grid (length and width are the same)
     * @param playerStart: The coordinates of where the player will start on a grid
     * @param enemies: A list of enemies on the grid
     */
    public Level(int gridSize, Position playerStart, List<Enemy> enemies){
        // Set grid size
        grid = new Grid(gridSize);
        player = new Player(playerStart);

        // Set initial positions of enemies
        this.enemies = enemies;
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
     * Method to test movement
     */
    public void render() {
        System.out.println("Player Position: [" + player.getX() + ", " + player.getY() + "]");
        System.out.println("Enemies:");
        for(int i = 0; i < enemies.size(); i++){
            System.out.println("[" + enemies.get(i).getX() + ", " + enemies.get(i).getY() + "]");
        }
        System.out.println(); // extra line
    }

    /**
     * Method to return Player object within a level, namely for the Player's position
     * @return: Player object
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * Method return List of enemies within a level, namely for the Enemy positions
     * @return: List of enemies
     */
    public List<Enemy> getEnemies(){
        return this.enemies;
    }

    public int getDimension(){
        return grid.getDimension();
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

