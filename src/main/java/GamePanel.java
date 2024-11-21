import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GamePanel class represents the panel where the actual game (grid, player, enemy, etc.) are rendered.
 */
public class GamePanel extends JPanel{
    private int numOfRows;
    private int numOfCols;
    private final int cellSize = 50;
    private Player player;
    private List<Enemy> enemies;
    private ArrayList<Objective> objectives;
    private Door door;
    private List<Wall> walls;
    private Position doorPosition;

    int lives;
    int score;
    int moves;

    /**
     * Constructor for GamePanel object.
     * @param level: The level that will be rendered
     */
    public GamePanel(Level level, int lives, int score, int moves) {
        // get information from level
        this.numOfRows = level.getNumOfRows();
        this.numOfCols = level.getNumOfCols();
        this.player = level.getPlayer();
        this.enemies = level.getEnemies();
        this.objectives = level.getObjectives();
        this.door = level.getDoor();
        this.walls = level.getWalls();
        this.doorPosition = level.getDoorPosition();

        this.lives = lives;
        this.score = score;
        this.moves = moves;
    }

    /**
     * Method to render all objects in the Level
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // Draw grid
        g.setColor(Color.lightGray);

        // Get dimension of panel and grid
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int gridWidth = numOfCols * cellSize;
        int gridHeight = numOfRows * cellSize;
        int uiBarHeight = 60;

        // Calculate offsets to render the grid centered and lower on the panel
        int xOffset = (panelWidth - gridWidth) / 2;
        int yOffset = (panelHeight - gridHeight) / 2 + uiBarHeight;

        for(int i = 0; i < numOfRows; i++){
            for(int j = 0; j < numOfCols; j++){
                ImageIcon icon = new ImageIcon(getClass().getResource("/Terrain/Swamp.png"));
                Image image = icon.getImage();
                g.drawImage(image, xOffset + j*cellSize, yOffset + i*cellSize, cellSize, cellSize, this);
            }
        }

        if(door != null){
            Door door = this.door;// If door exists, then render open door
            ImageIcon icon = new ImageIcon(getClass().getResource("/Terrain/Door_Open.png"));
            Image image = icon.getImage();
            g.drawImage(image,xOffset + door.getX()*cellSize, yOffset + door.getY()*cellSize, cellSize, cellSize, this);
        }
        else{ // else, render closed door
            ImageIcon icon = new ImageIcon(getClass().getResource("/Terrain/Door_Close.png"));
            Image image = icon.getImage();
            g.drawImage(image,xOffset + doorPosition.getX()*cellSize, yOffset + doorPosition.getY()*cellSize, cellSize, cellSize, this);
        }

        // Draw player
        String playerImgName = new String();
        if(player.getFacing() == Direction.RIGHT) {
            playerImgName = "/Player/Hero_Right_1.png";
        } else if(player.getFacing() == Direction.LEFT) {
            playerImgName = "/Player/Hero_Left_1.png";
        } else if(player.getFacing() == Direction.UP) {
            playerImgName = "/Player/Hero_Up_1.png";
        } else if(player.getFacing() == Direction.DOWN) {
            playerImgName = "/Player/Hero_Down_1.png";
        }

        if(player.isMatchPrevMove() % 2 == 0) {
            playerImgName = playerImgName.replace('1', '2');
        }
        ImageIcon playerIcon = new ImageIcon(getClass().getResource(playerImgName));
        Image playerImg = playerIcon.getImage();
        g.drawImage(playerImg, xOffset + player.getX() * cellSize, yOffset + player.getY() * cellSize, cellSize, cellSize, this);

        // Draw enemies
        g.setColor(Color.red);
        for(Enemy enemy: enemies){
            if(enemy instanceof StationaryEnemy){
                ImageIcon icon = new ImageIcon(getClass().getResource("/Enemies/Thorns.png"));
                Image image = icon.getImage();
                g.drawImage(image, xOffset + enemy.getX()*cellSize, yOffset + enemy.getY()*cellSize, cellSize, cellSize, this );
            }
            else{
                String enemyImgName = new String();
                if(enemy.getFacing() == Direction.RIGHT) {
                    enemyImgName = "/Enemies/Ghost_Right_1.png";
                } else if(enemy.getFacing() == Direction.LEFT) {
                    enemyImgName = "/Enemies/Ghost_Left_1.png";
                } else if(enemy.getFacing() == Direction.UP) {
                    enemyImgName = "/Enemies/Ghost_Up_1.png";
                } else if(enemy.getFacing() == Direction.DOWN) {
                    enemyImgName = "/Enemies/Ghost_Down_1.png";
                }
                if(enemy.isMatchPrevMove() % 2 == 0) {
                    enemyImgName = enemyImgName.replace('1', '2');
                }
                ImageIcon enemyIcon = new ImageIcon(getClass().getResource(enemyImgName));
                Image enemyImg = enemyIcon.getImage();
                g.drawImage(enemyImg, xOffset + enemy.getX() * cellSize, yOffset + enemy.getY() * cellSize, cellSize, cellSize, this);
            }
        }

        // Draw objectives
        for(Objective objective: objectives){
            if(objective.isMandatory()){ //if mandatory
                ImageIcon icon = new ImageIcon(getClass().getResource("/Objectives/Objective_Key.png"));
                Image image = icon.getImage();
                g.drawImage(image, xOffset + objective.getX()*cellSize, yOffset + objective.getY()*cellSize, cellSize, cellSize, this );
            }
            else{ // if bonus
                ImageIcon icon = new ImageIcon(getClass().getResource("/Objectives/Coin.png"));
                Image image = icon.getImage();
                g.drawImage(image, xOffset + objective.getX()*cellSize, yOffset + objective.getY()*cellSize, cellSize, cellSize, this );
            }
        }

        // Draw walls
        g.setColor(Color.lightGray);
        for(Wall wall: walls){
            ImageIcon icon = new ImageIcon(getClass().getResource("/Terrain/Tree.png"));
            Image image = icon.getImage();
            g.drawImage(image,xOffset + wall.getX()*cellSize, yOffset + wall.getY()*cellSize, cellSize, cellSize, this);
        }

        // Render UI header


    }

    /**
     * Updates positions of players and enemies, and whether objectives and doors exist in the current state. Then
     * re-renders the Grid to reflect the update.
     * @param level: Level to be updated
     */
    public void update(Level level, int lives, int score, int moves){
        this.lives = lives;
        this.score = score;
        this.moves = moves;
        this.player = level.getPlayer();
        this.enemies = level.getEnemies();
        this.objectives = level.getObjectives();
        this.door = level.getDoor();
        repaint();
    }


}
