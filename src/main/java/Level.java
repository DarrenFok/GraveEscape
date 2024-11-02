import java.util.List;
import java.util.Random;

public class Level {
    private int levelNumber;
    private List<Objective> objectives;
    private Player player;
    private List<Enemy> enemies;
    private List<Position> enemyPositions;
    private Grid grid;
    private int bonusPoints;
    private int tickCount;
    private boolean isComplete;

    /**
     * Constructor for Level object.
     * @param gridSize: Size of a square shaped grid (length and width are the same)
     * @param playerStart: The coordinates of where the player will start on a grid
     * @param enemies: A list of enemies on the grid
     */
    public Level(int gridSize, Position playerStart, List<Enemy> enemies){
        // Set grid size
        grid = new Grid(gridSize);

        // Set initial position of player
        player.setPosition(playerStart);

        // Set initial positions of enemies
        this.enemies = enemies;
        for(int i = 0; i < enemies.size(); i++){
            enemyPositions.set(i, enemies.get(i).getPosition());
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
                if(player.getY() > 0){
                    player.setPosition(new Position(player.getX(), player.getY()-1));
                }
                break;
            case DOWN:
                if(player.getY() < grid.getDimension()){
                    player.setPosition(new Position(player.getX(), player.getY()+1));
                }
                break;
            case LEFT:
                if(player.getX() > 0){
                    player.setPosition(new Position(player.getX()-1, player.getY()));
                }
                break;
            case RIGHT:
                if(player.getX() < grid.getDimension()){
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
        for(int i = 0; i < enemies.size(); i++){
            int direction = random.nextInt(4);
            switch(direction){
                case 0: // Moving up
                    if(enemies.get(i).getY() > 0){
                        enemyPositions.set(i, new Position(enemies.get(i).getX(), enemies.get(i).getY()-1));
                    }
                    break;
                case 1: // Moving down
                    if(enemies.get(i).getY() < grid.getDimension()){
                        enemyPositions.set(i, new Position(enemies.get(i).getX(), enemies.get(i).getY()+1));
                    }
                    break;
                case 2: // Moving left
                    if(enemies.get(i).getX() > 0){
                        enemyPositions.set(i, new Position(enemies.get(i).getX()-1, enemies.get(i).getY()));
                    }
                    break;
                case 3:
                    if(enemies.get(i).getX() < grid.getDimension()){
                        enemyPositions.set(i, new Position(enemies.get(i).getX()+1, enemies.get(i).getY()));
                    }
                    break;
            }
            enemies.get(i).setPosition(enemyPositions.get(i));
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
