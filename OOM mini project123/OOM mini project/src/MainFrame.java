 import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainFrame extends JFrame{
    JLabel Title;
    JButton b1,b2,b3;
    JPanel p;
    TextArea area;
    public static GameFrame GameFramegame;
    public MainFrame()
    {
        Title = new JLabel("BATTLESHIP ARCADE GAME");
        Title.setBounds(200,50,500,90);
        add(Title);
        area=new TextArea(20,70);
        area.append("                                                           About Game:                     \n");
        area.append("Name of the game is Battleship Arcade Game.\n");
        area.append("1.There is one personal spaceship(a Rectangle) and many enemy spaceships can come simultaneously\n");
        area.append("2.Each kill will lead to increase in score by 1 unit. \n");
        area.append("3.After scoring 20 consecutive scores,level of the game will increase.\n");
        area.append("4.With increase in level, speed of coming enemy spaceships will increase with the increase of enemies too.The highest level is 5.\n");
        area.append("5.If the spaceship gets smashed with any one of the enemy spaceship or got hit by a Enemy Fire,then game will get over.\n");
        area.append("6.Enemy spaceship will get destroyed on hit with one bullet and so as you.\n");
        area.append("\nControls:");
        area.append("\n" +
                "1) UP Arrow: Spaceship will move in upward direction.\n" +
                "2) Down Arrow: Spaceship will move in downward direction.\n" +
                "3) Right Arrow: Spaceship will move Right direction. \n" +
                "4) Left Arrow: Spaceship will move in left direction.\n" +
                "5) Space: Spaceship will fire one bullet.\n");
        b1=new JButton("START");
        b2=new JButton("EXIT");
        b3=new JButton("HELP");
        area.setEditable(false);
        b1.setBackground(Color.lightGray);
        b2.setBackground(Color.lightGray);
        b3.setBackground(Color.lightGray);
        b1.setBounds(250,290,120,60);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        GameFramegame = new GameFrame();
                        main.frame.dispose();
                    }
                });
            }
        });
        b2.setBounds(250,430,120,60);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        main.frame.dispose();
                    }
                });
            }
        });
        b3.setBounds(250,360,120,60);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(main.frame,area.getText());
                    }
                });
            }
        });
        add(b1);
        add(b2);
        add(b3);
        add(new JLabel(new ImageIcon(new ImageIcon("BG.jpg").getImage().getScaledInstance(623,900,Image.SCALE_DEFAULT))));
        setLayout(null);
        setVisible(true);
        setSize(623,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}