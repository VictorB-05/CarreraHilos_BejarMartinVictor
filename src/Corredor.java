import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Corredor extends JComponent implements Runnable{
    private final Image corredorImage;
    private static boolean fin = false;

    public Corredor(String imagen) throws IOException {
        super();
        this.corredorImage = ImageIO.read(Interfaz.class.getResource(imagen));
        this.setSize(400,300);

    }

    public static boolean isFin() {
        return fin;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(corredorImage, 8, 32, this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(corredorImage, 8, 32, this);
    }


    @Override
    public void run() {
        while(this.getY()<780 && !fin){
            try {
                this.setLocation(this.getX(), (int) (this.getY()+(Math.random()*10)+1));
                Thread.sleep(100);
//                System.out.println("hey"+this.getY());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        fin = true;
    }
}
