import javax.swing.JFrame;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Lets the window properly close when "X" is clicked
        window.setResizable(false);
        window.setTitle("Group 10 Game"); //Change when game name is thought of

        Game game = new Game();
        window.add(game);
        window.pack(); // causes window to fit the preffered size of its component

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        game.startGameThread();
    }    
}
