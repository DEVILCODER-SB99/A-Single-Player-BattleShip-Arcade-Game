import java.awt.geom.Rectangle2D;
public class EnemySpaceShip {
    private Rectangle2D.Double EnemyShip;
    private double PosX = 3.0, PosY = -60.0;
    private double Length = 50.0, Breadth = 50.0;
    public EnemySpaceShip(int n){
        EnemyShip = new Rectangle2D.Double(PosX + 56*n, PosY, Length, Breadth);
    }
    public Rectangle2D.Double getEnemyShip() {
        return EnemyShip;
    }
}
