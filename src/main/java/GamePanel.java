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

    /**
     * Constructor for GamePanel object.
     * @param numOfRows: The number of rows in the Level's Grid
     * @param numOfCols: The number of columns in the Level's Grid
     * @param player: Player object
     * @param enemies: List of enemies
     * @param objectives: List of objectives
     * @param door: Door object
     */
    public GamePanel(int numOfRows, int numOfCols, Player player, List<Enemy> enemies, ArrayList<Objective> objectives, Door door) {
        this.numOfRows = numOfRows;
        this.numOfCols = numOfCols;
        this.player = player;
        this.enemies = enemies;
        this.objectives = objectives;
        this.door = door;
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
                g.drawRect(j*cellSize, i*cellSize, cellSize, cellSize);
            }
        }

        // Draw player
        g.setColor(Color.blue);
        g.fillOval(player.getX()*cellSize, player.getY()*cellSize, cellSize, cellSize);

        // Draw enemies
        g.setColor(Color.red);
        for(Enemy enemy: enemies){
            g.fillRect(enemy.getX()*cellSize, enemy.getY()*cellSize, cellSize, cellSize);
        }

        // Draw objectives
        for(Objective objective: objectives){
            g.setColor(Color.green);
            if(objective.isMandatory()){
                g.setColor(Color.yellow);
            }
            g.fillOval(objective.getX()*cellSize, objective.getY()*cellSize, cellSize, cellSize);
        }

        if(door != null){
            Door door = this.door;
            g.setColor(Color.magenta);
            g.fillRect(door.getX()*cellSize, door.getY()*cellSize, cellSize, cellSize);
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
