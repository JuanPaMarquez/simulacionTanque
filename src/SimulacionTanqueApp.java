import opciones.BarraWindow;
import principal.TanqueConValvulaVisual;

import java.awt.BorderLayout;
import java.awt.Color;
//import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class SimulacionTanqueApp {

    public static void main(String[] args) {
 //SwingUtilities.invokeLater(new Runnable() {
            // Variables de posición para el arrastre de la ventana
       
           //// public void run() {

                JFrame frame = new JFrame("Simulación de Tanque con Válvula y Casita");
                TanqueConValvulaVisual panel = new TanqueConValvulaVisual();
                // panel.setBackground(new Color(0xeecfc7));
                panel.setBackground(Color.white);
                frame.add(panel);
                frame.setSize(800, 600); // Aumentar el tamaño para acomodar la tubería y la casa
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null); // Centrar la ventana
                frame.setUndecorated(true);

                // Crear un panel para actuar como barra de título personalizada
                BarraWindow barraTitulo = new BarraWindow(frame);          
                frame.add(barraTitulo, BorderLayout.NORTH);
                frame.setVisible(true);
          //  }
       // });
    }
}