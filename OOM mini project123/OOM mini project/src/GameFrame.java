import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameFrame extends JFrame implements ActionListener {
    public static Timer timer;
    public static Game game;
    public GameFrame(){
        super("Game");
        timer = new Timer(5, this);
        game = new Game();
        System.out.println("init");
        timer.start();
        /*try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }*/
        setSize(623,900);
        setLayout(new BorderLayout());
        add(game, BorderLayout.CENTER);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
    }
}
