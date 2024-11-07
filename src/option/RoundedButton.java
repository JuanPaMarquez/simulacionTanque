package option;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {

    private static final int RADIUS = 10; // Radio de las esquinas
    // Constructor para crear un botón elegante con icono, que ejecuta la acción
    // pasada

    public RoundedButton(String iconoTexto, JFrame frame, boolean esMinimizar) {
        // Usar el iconoTexto como el texto del botón
        super(iconoTexto);

        // Hacer que el botón sea transparente y sin bordes
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setForeground(Color.WHITE); // Color del texto del botón
        if (esMinimizar) {
            setBackground(new Color(52, 73, 94));
        } else {
            setBackground(new Color(231, 76, 60));
        }

        // Cambiar color al pasar el mouse
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.CYAN); // Color cuando el mouse pasa por encima
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(Color.WHITE); // Color cuando el mouse sale
            }
        });

        // Definir la acción del botón (minimizar o maximizar)
        addActionListener( _ -> {
            if (esMinimizar) {
                frame.setState(JFrame.ICONIFIED); // Minimizar la ventana
            } else {
                System.exit(0);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(new Color(52, 73, 94)); // Color de fondo al presionar
        } else {
            g.setColor(getBackground()); // Color de fondo normal
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), RADIUS, RADIUS);
        g.drawRoundRect(0, 0, getWidth(), getHeight(), RADIUS, RADIUS);
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 30); // Tamaño del botón
    }
}
