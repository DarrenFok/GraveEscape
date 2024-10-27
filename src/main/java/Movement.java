import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KeyListener interface is for receiving keyboard inputs 
public class Movement implements KeyListener{

    public boolean goUp;
    public boolean goDown;
    public boolean goLeft;
    public boolean goRight;

    @Override
    public void keyTyped(KeyEvent e) {
        //we do not use this method
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //returns the int keyCode belonging to the key input (i.e. 8 -> Backspace, 10 -> enter, etc..)

        //inputs for keys WSAD (movement)
        if (code == KeyEvent.VK_W){
            goUp = true;
        }
        if (code == KeyEvent.VK_S){
            goDown = true;
        }
        if (code == KeyEvent.VK_A){
            goLeft = true;
        }
        if (code == KeyEvent.VK_D){
            goRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); //returns the int keyCode belonging to the key input (i.e. 8 -> Backspace, 10 -> enter, etc..)

        //inputs for keys WSAD (movement)
        if (code == KeyEvent.VK_W){
            goUp = false;
        }
        if (code == KeyEvent.VK_S){
            goDown = false;
        }
        if (code == KeyEvent.VK_A){
            goLeft = false;
        }
        if (code == KeyEvent.VK_D){
            goRight = false;
        }
    }
    
}
