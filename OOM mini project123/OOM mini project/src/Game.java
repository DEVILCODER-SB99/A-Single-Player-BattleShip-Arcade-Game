import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
public class Game extends JComponent {
    private MySpaceShip MyShip;
    private ArrayList<Ellipse2D.Double> Fires = new ArrayList<>();
    private ArrayList<Ellipse2D.Double> EnemyFires = new ArrayList<>();
    private Ellipse2D.Double Fire, EnemyFire;
    private EnemySpaceShip EnemyShip;
    private ArrayList<Rectangle2D.Double> EnemySpaceShips = new ArrayList<>();
    private BufferedImage Buffer;
    private Random NoEnemy, EnemyPos;
    private Image BG;
    private int nF, ESize, Level = 1, Score = 0, GameStopFlag = 0, EscFlag = 0;
    public Game(){
        EscFlag = 0;
        GameStopFlag = 0;
        MyShip = new MySpaceShip();
        nF = 0;
        BG = Toolkit.getDefaultToolkit().getImage("BG.jpg");
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if(GameStopFlag == 0) {
                    int k = 0;
                    if (e.getID() == KeyEvent.KEY_PRESSED) {
                        k = e.getKeyCode();
                    }
                    switch (k) {
                        case KeyEvent.VK_UP:
                            System.out.println("Moved Up");
                            if (MyShip.getMyShip().y >= 0) {
                                MyShip.getMyShip().y = MyShip.getMyShip().y - 5;
                                repaint();
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            System.out.println("Moved Down");
                            if (MyShip.getMyShip().y <= getHeight() - 50) {
                                MyShip.getMyShip().y = MyShip.getMyShip().y + 5;
                                repaint();
                            }
                            break;
                        case KeyEvent.VK_LEFT:
                            if (MyShip.getMyShip().x >= 50) {
                                MyShip.getMyShip().x = MyShip.getMyShip().x - 56;
                                System.out.println("Moved Left");
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (MyShip.getMyShip().x <= getWidth() - 100) {
                                MyShip.getMyShip().x = MyShip.getMyShip().x + 56;
                                System.out.println("Moved Right");
                            }
                            break;
                        case KeyEvent.VK_SPACE:
                            Fire = new Ellipse2D.Double(MyShip.getMyShip().x + 20, MyShip.getMyShip().y + 5, 10, 10);
                            Fires.add(Fire);
                            System.out.println("added");
                            break;
                        case KeyEvent.VK_ESCAPE:
                            GameFrame.timer.stop();
                            JOptionPane.showMessageDialog(MainFrame.GameFramegame, "Game Paused");
                            GameStopFlag = 0;
                            GameFrame.timer.start();
                    }
                }
                return false;
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        if(Buffer == null){
            Buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g1 = (Graphics2D)Buffer.getGraphics();
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setColor(Color.BLACK);
        g1.fillRect(0,0,700,900);
        g1.drawImage(BG,0,0,null);
        g1.setColor(Color.GRAY);
        for(int i=0;i<12;i++) {
            g1.drawLine(56*i, 0, 56*i, 900);
        }
        g1.setColor(Color.CYAN);
        g1.fill(MyShip.getMyShip());
        g1.draw(MyShip.getMyShip());
        for(int i=0;i<Fires.size();i++) {
            g1.fill(Fires.get(i));
        }
        g1.setColor(Color.RED);
        for(int i=0;i<EnemySpaceShips.size();i++) {
            g1.fill(EnemySpaceShips.get(i));
        }
        for(int i=0;i<EnemyFires.size();i++){
            g1.fill(EnemyFires.get(i));
        }
        g1.setColor(Color.WHITE);
        g1.setFont(new Font("Century",Font.BOLD,30));
        g1.drawString("Score: " + Score, 10, 35);
        g1.drawString("Level: " + Level, 475, 35);
        g.drawImage(Buffer, 0, 0, null);
    }
    public void update(){
        if(Score >= 10){
            Level = 2;
        }
        if(Score >= 20){
            Level = 3;
        }
        if(Score >= 30){
            Level = 4;
        }
        if(Score >= 40){
            Level = 5;
        }
        for(int i=0;i<Fires.size();i++) {
            Fires.get(i).y = Fires.get(i).y - (2*Level);
            repaint();
        }
        for(int i=0;i<EnemyFires.size();i++) {
            EnemyFires.get(i).y = EnemyFires.get(i).y + (2 * Level);
            repaint();
        }
        for(int i=0;i<EnemySpaceShips.size();i++){
            EnemySpaceShips.get(i).y = EnemySpaceShips.get(i).y + Level;
            repaint();
        }
        for(int i=0;i<Fires.size();i++) {
            if(Fires.get(i).y < 0){
                Fires.remove(i);
                repaint();
            }
        }
        for(int i=0;i<EnemyFires.size();i++){
            if(EnemyFires.get(i).y > 900){
                EnemyFires.remove(i);
            }
        }
        for(int i=0;i<EnemySpaceShips.size();i++){
            if(EnemySpaceShips.get(i).y > 900){
                EnemySpaceShips.remove(i);
            }
        }
        for(int i=0;i<EnemySpaceShips.size();i++){
            for(int j=0;j<Fires.size();j++) {
                if (Fires.get(j).intersects(EnemySpaceShips.get(i))) {
                    Fires.remove(j);
                    EnemySpaceShips.remove(i);
                    System.out.println("Target Eliminated");
                    Score++;
                    repaint();
                    break;
                }
            }
        }
        for(int i=0;i<EnemyFires.size();i++){
            for(int j=0;j<Fires.size();j++){
                if(Fires.get(j).getBounds2D().intersects(EnemyFires.get(i).getBounds2D())){
                    Fires.remove(j);
                    EnemyFires.remove(i);
                    repaint();
                    break;
                }
            }
        }
        for(int i=0;i<EnemySpaceShips.size();i++){
            if(EnemySpaceShips.get(i).intersects(MyShip.getMyShip())){
                System.out.println("Game End");
                GameStopFlag = 1;
                GameFrame.timer.stop();
                JOptionPane.showMessageDialog(MainFrame.GameFramegame, "Score: "+ Score + "\nLevel Reached: "+ Level + "\nPress OK For Close \nReturn to Main Menu");
                MainFrame.GameFramegame.dispose();
                main.frame = new MainFrame();
            }
        }
        for(int i=0;i<EnemyFires.size();i++){
            if(EnemyFires.get(i).getBounds2D().intersects(MyShip.getMyShip())){
                System.out.println("Game End");
                GameStopFlag = 1;
                GameFrame.timer.stop();
                JOptionPane.showMessageDialog(MainFrame.GameFramegame, "Score: "+ Score + "\nLevel Reached: "+ Level + "\nPress OK or close \nto return to Main Menu");
                MainFrame.GameFramegame.dispose();
                main.frame = new MainFrame();
            }
        }
        if(EnemySpaceShips.size() == 0 || EnemySpaceShips.get(EnemySpaceShips.size()-1).y > 550 - (100*(Level-1))){
            ESize = EnemySpaceShips.size();
            NoEnemy = new Random();
            int n = NoEnemy.nextInt(Level*2 +1) + 1;
            System.out.println(n);
            for(int i=0;i<n;i++){
                EnemyRandomPosition();
            }
        }
        double FireZone = 0.0;
        if(EnemySpaceShips.size()>0) {
            double Eno = EnemySpaceShips.get(0).getY();
            for (int i = 0; i < EnemySpaceShips.size(); i++) {
                if(Level == 1){
                    FireZone = 300.0;
                }
                if(Level == 2){
                    FireZone = 250.0;
                }
                if(Level == 3){
                    FireZone = 350.0;
                }
                if(Level == 4){
                    FireZone = 300.0;
                }
                if(Level == 5){
                    FireZone = 250;
                }
                if(EnemySpaceShips.get(i).y == Eno && EnemySpaceShips.get(i).y == FireZone){
                    EnemyFire = new Ellipse2D.Double(EnemySpaceShips.get(i).x + 20, EnemySpaceShips.get(i).y + 50, 10, 10);
                    EnemyFires.add(EnemyFire);
                }
            }
        }
        repaint();
    }
    @Override
    public void update(Graphics g) {
        paint(g);
    }

        public void EnemyRandomPosition(){
            int flag = 0;
            EnemyPos = new Random();
            int n = EnemyPos.nextInt(11);
            if(ESize == 0) {
                for (int i = 0; i < EnemySpaceShips.size(); i++) {
                    double CPos = (EnemySpaceShips.get(i).x - 3) / 56;
                    if ((int) CPos == n) {
                        flag = 1;
                        break;
                    }
                }
            }
            else{
                for (int i = ESize; i < EnemySpaceShips.size(); i++) {
                    double CPos = (EnemySpaceShips.get(i).x - 3) / 56;
                    if ((int) CPos == n) {
                        flag = 1;
                        break;
                    }
                }
            }
            if(flag == 1){
                EnemyRandomPosition();
            }
            else{
                EnemyShip = new EnemySpaceShip(n);
                EnemySpaceShips.add(EnemyShip.getEnemyShip());
            }
        }
}