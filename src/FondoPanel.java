import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FondoPanel extends JPanel{
    private final Image backgroundImage;

    public FondoPanel(String imagePath) throws IOException {
        backgroundImage = ImageIO.read(Interfaz.class.getResource(imagePath));
        setOpaque(false); // Permitir transparencia
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
