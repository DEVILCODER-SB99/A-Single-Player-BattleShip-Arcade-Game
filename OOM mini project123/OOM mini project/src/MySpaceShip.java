import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MySpaceShip {
    private Rectangle2D.Double MyShip;
    private double PosX = 283, PosY = 780.0;
    private double Length = 50.0, Breadth = 50.0;

    public MySpaceShip(){
        MyShip = new Rectangle2D.Double(PosX, PosY, Length, Breadth);
    }

    public Rectangle2D.Double getMyShip() {
        return MyShip;
    }
}
