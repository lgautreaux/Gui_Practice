import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        //JOptionPane.showMessageDialog(null,"Hello World", "Hello World Application", 2);
        // JFrame jFrame = new JFrame();
        // jFrame.setSize(1000, 1000);
        // jFrame.setLocation(200, 200);
        // jFrame.setVisible(true);

        //MyJFrame jFrame1 = new MyJFrame("Testing the GUI");

        // JPanel jPanel = new JPanel();
        // jPanel.add(new JButton());
        // jFrame1.setContentPane(jPanel);

        // MyJFrame jFrame2 = new MyJFrame();
        setLookAndFeel();
        TicTacToeGame ticTacToeGame = new TicTacToeGame();
    }
    
    static void setLookAndFeel() {
        try {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
        UIManager.setLookAndFeel(info.getClassName());
        break;
        }
        }
        } catch (Exception e) {
        // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        }
}