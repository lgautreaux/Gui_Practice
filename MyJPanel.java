import java.awt.Graphics;

import javax.swing.JPanel;

public class MyJPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawString("Hello World!", 20, 30);
        g.drawString("This is our Java course",4, 4);
    }
}