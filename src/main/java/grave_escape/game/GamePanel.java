package grave_escape.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import grave_escape.enemy.Enemy;
import grave_escape.enemy.StationaryEnemy;
import grave_escape.levels.Level;
import grave_escape.objectives.Objective;
import grave_escape.player.Player;
import grave_escape.structure.Door;
import grave_escape.structure.Position;
import grave_escape.structure.Wall;

/**
 * game.GamePanel class represents the panel where the actual game (grid, player, enemy, etc.) are rendered.
 */
public class GamePanel extends JPanel{

    /**
     * The number of rows in the game grid.
     */
    private int numOfRows;

    /**
     * The number of columns in the game grid.
     */
    private int numOfCols;

    /**
     * The grid of the game, which contains all the cells where elements like player, enemies, and walls are placed.
     */
    private Grid grid;

    /**
     * The size of each cell in the grid.
     */
    private final int cellSize = 50;

    /**
     * The player object, representing the player character in the game.
     */
    private Player player;

    /**
     * The list of enemies in the game, which are the obstacles for the player.
     */
    private List<Enemy> enemies;

    /**
     * The list of objectives that the player needs to collect to win or progress in the game.
     */
    private ArrayList<Objective> objectives;

    /**
     * The door object representing the exit point of the game level.
     */
    private Door door;

    /**
     * The list of walls in the game, which block the movement of the player and enemies.
     */
    private List<Wall> walls;

    /**
     * The position of the door in the grid.
     */
    private Position doorPosition;

    /**
     * The amount of lives the player has left, affects how many hearts will be rendered in the header UI.
     */
    int lives;
    /**
     * The score the player has. This will be rendered in the header UI.
     */
    int score;
    /**
     * The amount of moves the player has made. This will be rendered in the header UI.
     */
    int moves;
    /**
     * The name of the Level that will be rendered in the header UI.
     */
    String levelName;
    /**
     * The name of the Difficulty of the Level that will be rendered in the header UI.
     */
    String difficulty;
    /**
     * The height of the UI header in pixels.
     */
    int uiHeaderHeight = 130;

    /**
     * Constructor for game.GamePanel object.
     * @param level The level that will be rendered.
     * @param lives The amount of lives the player has.
     * @param score The score that the player collects.
     * @param moves The number of moves made.
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
     * Method to render all objects in the levels.Level
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // Paint background image
        Image gameBackground = new ImageIcon(getClass().getResource(Values.GAME_BACKGROUND)).getImage();
        g.drawImage(gameBackground, 0, 130, 1349, 599, this);

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
                ImageIcon icon = new ImageIcon(getClass().getResource(Values.SWAMP));
                Image image = icon.getImage();
                g.drawImage(image, xOffset + j*cellSize, yOffset + i*cellSize, cellSize, cellSize, this);
            }
        }

        if(door != null){
            Door door = this.door;// If door exists, then render open door
            ImageIcon icon = new ImageIcon(getClass().getResource(Values.DOOR_OPEN));
            Image image = icon.getImage();
            g.drawImage(image,xOffset + door.getX()*cellSize, yOffset + door.getY()*cellSize, cellSize, cellSize, this);
        }
        else{ // else, render closed door
            ImageIcon icon = new ImageIcon(getClass().getResource(Values.DOOR_CLOSE));
            Image image = icon.getImage();
            g.drawImage(image,xOffset + doorPosition.getX()*cellSize, yOffset + doorPosition.getY()*cellSize, cellSize, cellSize, this);
        }

        // Draw player
        String playerImgName = new String();
        if(player.getFacing() == Direction.RIGHT) {
            playerImgName = Values.HERO_RIGHT_1;
        } else if(player.getFacing() == Direction.LEFT) {
            playerImgName = Values.HERO_LEFT_1;
        } else if(player.getFacing() == Direction.UP) {
            playerImgName = Values.HERO_UP_1;
        } else if(player.getFacing() == Direction.DOWN) {
            playerImgName = Values.HERO_DOWN_1;
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
                ImageIcon icon = new ImageIcon(getClass().getResource(Values.OBJECTIVE_KEY));
                Image image = icon.getImage();
                g.drawImage(image, xOffset + objective.getX()*cellSize, yOffset + objective.getY()*cellSize, cellSize, cellSize, this );
            }
            else{ // if bonus
                ImageIcon icon = new ImageIcon(getClass().getResource(Values.OBJECTIVE_COIN));
                Image image = icon.getImage();
                g.drawImage(image, xOffset + objective.getX()*cellSize, yOffset + objective.getY()*cellSize, cellSize, cellSize, this );
            }
        }

        // Draw enemies
        for(Enemy enemy: enemies){
            if(enemy instanceof StationaryEnemy){
                ImageIcon icon = new ImageIcon(getClass().getResource(Values.THORN_ENEMY));
                Image image = icon.getImage();
                g.drawImage(image, xOffset + enemy.getX()*cellSize, yOffset + enemy.getY()*cellSize, cellSize, cellSize, this );
            }
            else{
                String enemyImgName = Values.GHOST_DOWN_1;
                if(enemy.getFacing() == Direction.RIGHT) {
                    enemyImgName = Values.GHOST_RIGHT_1;
                } else if(enemy.getFacing() == Direction.LEFT) {
                    enemyImgName = Values.GHOST_LEFT_1;
                } else if(enemy.getFacing() == Direction.UP) {
                    enemyImgName = Values.GHOST_UP_1;
                } else if(enemy.getFacing() == Direction.DOWN) {
                    enemyImgName = Values.GHOST_DOWN_1;
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
            ImageIcon icon = new ImageIcon(getClass().getResource(Values.TREE_TERRAIN));
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
        ImageIcon heartIcon = new ImageIcon(getClass().getResource(Values.HEART_UI));
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
                ImageIcon icon = new ImageIcon(getClass().getResource(Values.OBJECTIVE_KEY_UI));
                Image image = icon.getImage();
                g.drawImage(image, xPos, 60, objWidth, objWidth, this );
            }
            else{ // if bonus
                ImageIcon icon = new ImageIcon(getClass().getResource(Values.OBJECTIVE_COIN_UI));
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
     * re-renders the game.Grid to reflect the update.
     * @param level Level to be updated.
     * @param lives The amount of lives the player has updated.
     * @param score The score that the player collects updated.
     * @param moves The number of moves made updated.
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
