import javax.swing.*;
public class Calling {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{ new MainFrame();});
    }
}