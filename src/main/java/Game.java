import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

//Runnable is to allow the use of Thread
public class Game extends JPanel implements Runnable{
    //Screen Settings
    final int baseTileSize = 16; //change depending on the number of pixels of characters
    final int scale = 3;//to make everything look of reasonable size relative to the screen

    final int tileSize = baseTileSize * scale; 
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenLength = tileSize * maxScreenRow;

    private List<Level> levels;
    private Level currentLevel;
    private int livesRemaining;
    private int score;
    private Difficulty difficulty;
    private GameMode gameMode;

    int FPS = 60;
    Movement keyH = new Movement();
    Thread gameThread; //once thread starts, it keeps the program running until stopped (keeps updating the current screen)


    //Setting the initial position of the player (can move this section to the position class later on)
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;

    //Game constructor
    public Game(){
        this.setPreferredSize(new Dimension(screenWidth, screenLength)); //setting the Dimensions
        this.setBackground(Color.gray); //setting background color
        //Draws graphics onto an offscreen buffer, moves the fully rendered image to the screen in one movement
        this.setDoubleBuffered(true); 
        this.addKeyListener(keyH);
        this.setFocusable(true); //GamePanel focuses to recieve a key input
    }

    public void startGameThread() {
        gameThread = new Thread(this); //passing game class to Thread constructor (instantiating the thread)
        gameThread.start();
    }

    @Override //when we start gameThread it automatically calls run()
    public void run() {
        while (gameThread != null){
            System.out.println("Game is continuously running");

            double drawInterval = 1000000000/FPS; //0.01667 seconds
            //nanoTime() returns current system time
            double nextDrawTime = System.nanoTime() + drawInterval;

            //two things that happen in this loop (corresponds to FPS)
            //1: update information continuously (i.e. position)
            update();
            //2: draw screen with updated info
            repaint(); //calling paintComponent
            //constantly updates and repaints
            try {
            double remainingTime = nextDrawTime - System.nanoTime(); //gives how much more time remaining until the nextDrawTime
            remainingTime /= 1000000; //converting nano to mili

            if (remainingTime < 0){
                remainingTime = 0;
            }

            Thread.sleep((long) remainingTime); //sleep pauses the loop so that it wont do anything until the sleep is over

            nextDrawTime += drawInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void update (){
        //changing the position of the character depending on the key input
        if(keyH.goUp == true){
            playerY -= playerSpeed;
        }
        if(keyH.goDown == true){
            playerY += playerSpeed;
        }
        if(keyH.goLeft == true){
            playerX -= playerSpeed;
        }
        if(keyH.goRight == true){
            playerX += playerSpeed;
        }
    }
    //built in method in java (standard method to draw things on JPanel)
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Graphics2D class extends Graphics class providing more control over coordinate transforms and color management
        Graphics2D g2 = (Graphics2D)g; 
        //our character object
        g2.setColor(Color.black);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose(); //releases all resources used by this graphics (saves memory)
    }

    //Functions for Game
    public void startGame(){}
    public void endGame(){}
    public void resetGame(){}
    public void updateScore(){}
    public void loseLife(){}

}
