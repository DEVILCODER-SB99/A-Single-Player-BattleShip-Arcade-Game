import javax.swing.*;

public class main {
    public static MainFrame frame;
    public static void main(String args[]) {
        SwingUtilities.invokeLater(()->{ frame = new MainFrame();});
    }
}
