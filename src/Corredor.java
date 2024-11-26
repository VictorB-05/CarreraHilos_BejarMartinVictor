import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Corredor extends JComponent implements Runnable{
    private final Image corredorImage;
    private static boolean fin = false;
    private int velocidad = 5;
    private String nombre;

    public Corredor(String imagen) throws IOException {
        super();
        this.corredorImage = ImageIO.read(Interfaz.class.getResource(imagen));
        this.setSize(400,300);

    }

    public Corredor(String imagen,String nombre) throws IOException {
        super();
        this.corredorImage = ImageIO.read(Interfaz.class.getResource(imagen));
        this.setSize(400,300);
        this.nombre = nombre;
    }

    public static boolean isFin() {
        return fin;
    }

    public String getNombre() {
        return nombre;
    }

    public void reIniciar(){
        fin = false;
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
        while(this.getY()<700 && !fin){
            try {
                cambioV();
                this.setLocation(this.getX(), (this.getY()+velocidad));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        fin = true;
    }

    @Override
    public String toString() {
        return "Corredor: " +
                nombre + " recorrido: " + this.getY();
    }

    private void cambioV() {
        switch ((int) ((Math.random()*3)+1)){
            case 1:
                if(velocidad<10){
                    velocidad++;
                }
                break;
            case 2:
                if(velocidad>2){
                    velocidad--;
                }
                break;
            case 3:
                if(velocidad<4){
                    velocidad++;
                }else if(velocidad>7){
                    velocidad--;
                }
        }
    }
}
