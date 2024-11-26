import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Interfaz extends JFrame {

    private final Image backgroundImage = ImageIO.read(Interfaz.class.getResource("img/carrera.png"));
    Corredor coche1;
    Corredor coche2;
    Corredor coche3;
    Corredor coche4;
    JButton iniciarB;
    JButton reiniciarB;


    public Interfaz() throws IOException, InterruptedException {
        super("Carreras hilos");
        setSize(new Dimension(640,1030));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        Image img = ImageIO.read(getClass().getResource("img/icono.png"));
        setIconImage(img);
        AddElementos();
    }

    private void AddElementos() throws IOException {

        // Agregar fondo personalizado
        FondoPanel fondoPanel = new FondoPanel("img/carrera.png");
        fondoPanel.setBounds(0, 0, 624, 1000);
        fondoPanel.setLayout(null); // Permite posicionamiento absoluto
        getContentPane().add(fondoPanel);

        // los corredores
        coche1 = new Corredor("img/avaismarus.png","avasimarus");
        fondoPanel.add(coche1);

        coche2 = new Corredor("img/Luigi.png","mario and luigi");
        fondoPanel.add(coche2);

        coche3 = new Corredor("img/pibeChill.png","chill");
        fondoPanel.add(coche3);

        coche4 = new Corredor("img/miniPekka.png","mini P.E.K.K.A.");
        fondoPanel.add(coche4);

        //Boton para iniciar la carrera
        iniciarB = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("img/start.png"));
            iniciarB.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        iniciarB.setVisible(true);
        iniciarB.addActionListener(e -> correr());
        iniciarB.setBounds(0,0,60,70);
        fondoPanel.add(iniciarB);

        //Boton para reinicar las posiciones
        reiniciarB = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("img/restart.png"));
            reiniciarB.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        reiniciarB.setVisible(true);
        reiniciarB.addActionListener(e -> posicionInicio());
        reiniciarB.setBounds(560,0,65,70);
        reiniciarB.setBackground(Color.green);
        fondoPanel.add(reiniciarB);

        posicionInicio();
    }

    private void correr(){
        reiniciarB.setEnabled(false);
        iniciarB.setEnabled(false);
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

    private void posicionInicio(){
        coche1.reIniciar();
        coche1.setBounds(60,0,145,200);
        coche2.setBounds(190,0,145,200);
        coche3.setBounds(300,0,145,200);
        coche4.setBounds(440,0,145,200);
        iniciarB.setEnabled(true);
    }

    private void win(){
        try {
            while (true){
                Thread.sleep(200);// para limitar las comprobaciones
                if(Corredor.isFin()){
                    JOptionPane.showMessageDialog(null,"Gano el corredor:"+resultado());
                    break;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        reiniciarB.setEnabled(true);
    }

    private Corredor comprobar() {
        if (coche1.getY() > coche2.getY()) {
            if (coche1.getY() > coche3.getY()) {
                if (coche1.getY() > coche4.getY()) {
                    return coche1;
                } else {
                    return coche4;
                }
            } else {
                if (coche3.getY() > coche4.getY()) {
                    return coche3;
                } else {
                    return coche4;
                }
            }
        } else {
            if (coche2.getY() > coche3.getY()) {
                if (coche2.getY() > coche4.getY()) {
                    return coche2;
                } else {
                    return coche4;
                }
            } else {
                if (coche3.getY() > coche4.getY()) {
                    return coche3;
                } else {
                    return coche4;
                }
            }
        }
    }
    private String resultado(){
        String aux = "";
        aux += comprobar().getNombre() + "\n";
        aux += coche1.toString()+ "\n";
        aux += coche2.toString()+ "\n";
        aux += coche3.toString()+ "\n";
        aux += coche4.toString()+ "\n";
        return aux;
    }
}
