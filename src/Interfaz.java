import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Interfaz extends JFrame {

    private final Image backgroundImage = ImageIO.read(Interfaz.class.getResource("img/carrera.png"));
    Corredor coche1;
    Corredor coche2;
    Corredor coche3;
    Corredor coche4;

    public Interfaz() throws IOException, InterruptedException {
        super("Carreras hilos");
        setSize(new Dimension(624,1030));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        AddElementos();
    }

    private void AddElementos() throws IOException {

        // Agregar fondo personalizado
        FondoPanel fondoPanel = new FondoPanel("img/carrera.png");
        fondoPanel.setBounds(0, 0, 624, 1000);
        fondoPanel.setLayout(null); // Permitir posicionamiento absoluto
        getContentPane().add(fondoPanel);

        coche1 = new Corredor("img/avaismarus.png");
        coche1.setBounds(50,30,145,180);
        fondoPanel.add(coche1);

        coche2 = new Corredor("img/Luigi.png");
        coche2.setBounds(190,30,145,180);
        fondoPanel.add(coche2);

        coche3 = new Corredor("img/pibeChill.png");
        coche3.setBounds(300,30,145,180);
        fondoPanel.add(coche3);

        coche4 = new Corredor("img/miniPekka.png");
        coche4.setBounds(440,30,145,180);
        fondoPanel.add(coche4);

        Thread t1 = new Thread(coche1);
        t1.start();
        Thread t2 = new Thread(coche2);
        t2.start();
        Thread t3 = new Thread(coche3);
        t3.start();
        Thread t4 = new Thread(coche4);
        t4.start();
        Thread t5 = new Thread(this::win);
        t5.start();
    }

    private void win(){
        while (true){
            if(Corredor.isFin()){
                JOptionPane.showMessageDialog(null,"Gano el corredor:"+comprobar());
                break;
            }else{
                System.out.println("paco");
            }
        }

    }
    private String comprobar(){
        if(coche1.getY()>coche2.getY()){
            if (coche1.getY()>coche3.getY()){
                if (coche1.getY()>coche4.getY()){
                    return "coche1";
                }else{
                    return "coche4";
                }
            }else{
                if(coche3.getY()>coche4.getY()){
                    return "coche3";
                }else{
                    return "coche4";
                }
            }
        }else{
            if (coche2.getY()>coche3.getY()){
                if (coche2.getY()>coche4.getY()){
                    return "coche2";
                }else{
                    return "coche4";
                }
            }else{
                if(coche3.getY()>coche4.getY()){
                    return "coche3";
                }else{
                    return "coche4";
                }
            }
        }
    }
}
