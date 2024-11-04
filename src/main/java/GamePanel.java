import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel{
    private int gridSize;
    private final int cellSize = 50;
    private Player player;
    private List<Enemy> enemies;
    private ArrayList<Objective> objectives;
    private Door door;

    public GamePanel(int gridSize, Player player, List<Enemy> enemies, ArrayList<Objective> objectives, Door door) {
        this.gridSize = gridSize;
        this.player = player;
        this.enemies = enemies;
        this.objectives = objectives;
        this.door = door;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // Draw grid
        g.setColor(Color.lightGray);
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
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

    public void update(Player player, List<Enemy> enemies, ArrayList<Objective> objectives, Door door){
        this.player = player;
        this.enemies = enemies;
        this.objectives = objectives;
        this.door = door;
        repaint();
    }


}
