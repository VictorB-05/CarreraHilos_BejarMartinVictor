import javax.swing.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Interfaz().setVisible(true);
                } catch (IOException | InterruptedException e) {
                    JOptionPane.showMessageDialog(null,"Error con los recurosos");
                    e.printStackTrace();
                }
            }
        });
    }
}