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

    /**
     * Constructor for GamePanel object.
     * @param numOfRows: The number of rows in the Level's Grid
     * @param numOfCols: The number of columns in the Level's Grid
     * @param player: Player object
     * @param enemies: List of enemies
     * @param objectives: List of objectives
     * @param door: Door object
     */
    public GamePanel(
            int numOfRows,
            int numOfCols,
            Player player,
            List<Enemy> enemies,
            ArrayList<Objective> objectives,
            Door door,
            List<Wall> walls,
            Position doorPosition
    ) {
        this.numOfRows = numOfRows;
        this.numOfCols = numOfCols;
        this.player = player;
        this.enemies = enemies;
        this.objectives = objectives;
        this.door = door;
        this.walls = walls;
        this.doorPosition = doorPosition;
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
        for(int i = 0; i < numOfRows; i++){
            for(int j = 0; j < numOfCols; j++){
                ImageIcon icon = new ImageIcon(getClass().getResource("/Terrain/Swamp.png"));
                Image image = icon.getImage();
                g.drawImage(image, j*cellSize, i*cellSize, cellSize, cellSize, this);
            }
        }

        if(door != null){
            Door door = this.door;// If door exists, then render open door
            ImageIcon icon = new ImageIcon(getClass().getResource("/Terrain/Door_Open.png"));
            Image image = icon.getImage();
            g.drawImage(image,door.getX()*cellSize, door.getY()*cellSize, cellSize, cellSize, this);
        }
        else{ // else, render closed door
            ImageIcon icon = new ImageIcon(getClass().getResource("/Terrain/Door_Close.png"));
            Image image = icon.getImage();
            g.drawImage(image,doorPosition.getX()*cellSize, doorPosition.getY()*cellSize, cellSize, cellSize, this);
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
        g.drawImage(playerImg, player.getX() * cellSize, player.getY() * cellSize, cellSize, cellSize, this);

        // Draw enemies
        g.setColor(Color.red);
        for(Enemy enemy: enemies){
            if(enemy instanceof StationaryEnemy){
                ImageIcon icon = new ImageIcon(getClass().getResource("/Enemies/Thorns.png"));
                Image image = icon.getImage();
                g.drawImage(image, enemy.getX()*cellSize, enemy.getY()*cellSize, cellSize, cellSize, this );
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
                g.drawImage(enemyImg, enemy.getX() * cellSize, enemy.getY() * cellSize, cellSize, cellSize, this);
            }
        }

        // Draw objectives
        for(Objective objective: objectives){
            if(objective.isMandatory()){ //if mandatory
                ImageIcon icon = new ImageIcon(getClass().getResource("/Objectives/Objective_Key.png"));
                Image image = icon.getImage();
                g.drawImage(image, objective.getX()*cellSize, objective.getY()*cellSize, cellSize, cellSize, this );
            }
            else{ // if bonus
                ImageIcon icon = new ImageIcon(getClass().getResource("/Objectives/Coin.png"));
                Image image = icon.getImage();
                g.drawImage(image, objective.getX()*cellSize, objective.getY()*cellSize, cellSize, cellSize, this );
            }
        }

        // Draw walls
        g.setColor(Color.lightGray);
        for(Wall wall: walls){
            ImageIcon icon = new ImageIcon(getClass().getResource("/Terrain/Tree.png"));
            Image image = icon.getImage();
            g.drawImage(image,wall.getX()*cellSize, wall.getY()*cellSize, cellSize, cellSize, this);
        }
    }

    /**
     * Updates positions of players and enemies, and whether objectives and doors exist in the current state. Then
     * re-renders the Grid to reflect the update.
     * @param player: Updated Player object
     * @param enemies: Enemy list containing updated Enemy positions
     * @param objectives: Objective list containing updated Objective states
     * @param door: Updated Door object
     */
    public void update(Player player, List<Enemy> enemies, ArrayList<Objective> objectives, Door door){
        this.player = player;
        this.enemies = enemies;
        this.objectives = objectives;
        this.door = door;
        repaint();
    }


}
