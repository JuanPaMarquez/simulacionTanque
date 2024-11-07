package option;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class BarraWindow extends javax.swing.JPanel {

    int posX = 0;
    int posY = 0;

    public BarraWindow(JFrame frame) {

        setBackground(new Color(44, 62, 80));
        setPreferredSize(new Dimension(400, 40));
        setLayout(new BorderLayout());

        // Crear botones para minimizar, maximizar y cerrar
        RoundedButton btnMinimizar = new RoundedButton("_", frame, true);

        RoundedButton btnCerrar = new RoundedButton("X", frame, false);
        JLabel title = new JLabel(" Simulación de Tanque con Válvula y Casita");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("times new roman", Font.BOLD, 20));
        // Acción del botón Cerrar

        // Añadir botones a la barra de título personalizada
        add(title, BorderLayout.WEST);
        // Crear un panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBounds(0, 0, 120, 30);
        panelBotones.setBackground(new Color(44, 62, 80));
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnMinimizar);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.EAST);
        // Añadir el panel de barra de título al JFrame

        // Hacer la barra de título arrastrable para mover la ventana
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
            }
        });
    }
}
