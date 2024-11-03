import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.List;

public class GamePanel extends JPanel{
    private int gridSize;
    private final int cellSize = 50;
    private Player player;
    private List<Enemy> enemies;

    public GamePanel(int gridSize, Player player, List<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.gridSize = gridSize;
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
    }

    public void update(Player player, List<Enemy> enemies){
        this.player = player;
        this.enemies = enemies;
        repaint();
    }


}
