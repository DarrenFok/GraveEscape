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
    String levelName;
    String difficulty;

    int uiHeaderHeight = 130;

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

        this.levelName = level.getLevelName();
        this.difficulty = level.getDifficulty();

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

        // Calculate offsets to render the grid centered and lower on the panel
        int xOffset = (panelWidth - gridWidth) / 2;
        int yOffset = (panelHeight - gridHeight) / 2 + uiHeaderHeight /2;

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

        // Draw enemies
        for(Enemy enemy: enemies){
            if(enemy instanceof StationaryEnemy){
                ImageIcon icon = new ImageIcon(getClass().getResource("/Enemies/Thorns.png"));
                Image image = icon.getImage();
                g.drawImage(image, xOffset + enemy.getX()*cellSize, yOffset + enemy.getY()*cellSize, cellSize, cellSize, this );
            }
            else{
                String enemyImgName = "/Enemies/Ghost_Down_1.png";
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

        // Draw walls
        g.setColor(Color.lightGray);
        for(Wall wall: walls){
            ImageIcon icon = new ImageIcon(getClass().getResource("/Terrain/Tree.png"));
            Image image = icon.getImage();
            g.drawImage(image,xOffset + wall.getX()*cellSize, yOffset + wall.getY()*cellSize, cellSize, cellSize, this);
        }

        // Render UI header
        g.setColor(new Color(26,35,21));
        g.fillRect(0,0, getWidth(), uiHeaderHeight);  // Background of header

        g.setColor(Color.lightGray);
        // Render level and difficulty
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString(levelName, 20, 50);
        g.drawString(difficulty, 20, 85);

        // Lives
        g.drawString("Lives", 220, 45);
        ImageIcon heartIcon = new ImageIcon(getClass().getResource("/In-Game UI Header/heart.png"));
        Image heartImage = heartIcon.getImage();
        for(int i = 0; i < lives; i++){
            g.drawImage(heartImage, 220 + (i*60), 60, 50, 50, this);
        }

        // Objectives
        g.drawString("Objectives", 600, 45);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        int objWidth = 50;
        int objSpace = 10;
        int totalWidth = objectives.size() * objWidth + (objectives.size()-1) * objSpace;
        int startX = 675 - (totalWidth / 2);
        for(int i = 0; i < objectives.size(); i++){
            int xPos = startX + i * (objWidth + objSpace);
            if(objectives.get(i).isMandatory()){ //if mandatory
                ImageIcon icon = new ImageIcon(getClass().getResource("/In-Game UI Header/Objective_Key_transparent.png"));
                Image image = icon.getImage();
                g.drawImage(image, xPos, 60, objWidth, objWidth, this );
            }
            else{ // if bonus
                ImageIcon icon = new ImageIcon(getClass().getResource("/In-Game UI Header/Better_Coin_transparent.png"));
                Image image = icon.getImage();
                g.drawImage(image, xPos, 60, objWidth, objWidth, this );
            }
        }

        // Moves
        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.drawString("Moves:", 900, 68);
        g.drawString(String.valueOf(moves), 1000, 68);

        // Score
        g.drawString("Score:", 1150, 68);
        g.drawString(String.valueOf(score), 1235, 68);

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
