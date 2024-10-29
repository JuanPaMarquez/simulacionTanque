package opciones;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import principal.TanqueConValvulaVisual;
// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;

public class paneldeOpciones extends JPanel {

    public JButton botonInicio = new JButton("Iniciar");
    JButton botonFinalizar = new JButton("Finalizar");
    JCheckBox automatizado = new JCheckBox("Automatico", true);
    JCheckBox manual = new JCheckBox("Manual");

    public JPanel menu() {

        // ! Creación y customización del panel
        JPanel panelmenu = new JPanel();
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 5);
        panelmenu.setBackground(new Color(0xD3D3D3));
        panelmenu.setLayout(null);

        // ! Ubicacion del panel en la vetana principal
        int ubicacionX = 450;
        int ubicacionY = 50;
        panelmenu.setBounds(ubicacionX, ubicacionY, 300, 150);

        ButtonGroup comunicacion = new ButtonGroup();
        // ! Creacion de los elementos del menu
        botonInicio.setSelected(false);
        botonFinalizar.setSelected(true);

        JLabel Titulomenu = new JLabel("Panel de Control");
        Titulomenu.setForeground(Color.BLACK);
        Titulomenu.setFont(new Font("Arial Black", Font.BOLD, 16));

        comunicacion.add(automatizado);
        comunicacion.add(manual);

        // ? Eventos del boton Inicio
        MouseListener EventsButtonInicio = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                botonInicio.setBackground(new Color(0x0cf204));
                botonFinalizar.setBackground(new Color(0x6598a4));
                botonInicio.setSelected(true);
                TanqueConValvulaVisual llamar1 = new TanqueConValvulaVisual();
                llamar1.paintComponents(getGraphics());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        // ? Eventos del boton Finalizar
        MouseListener EventsButtonFinalizar = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                botonFinalizar.setBackground(new Color(0xde0505));
                botonInicio.setBackground(new Color(0x6598a4));
                botonInicio.setSelected(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        // ActionListener ListeneButtonInicio = new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // if (botonInicio.isSelected()) {
        // botonFinalizar.setBackground(new Color(0x6598a4));
        // }
        // if (botonFinalizar.isSelected()) {
        // botonInicio.setBackground(new Color(0x6598a4));
        // }
        // }
        // };

        // ! Diseño de los Button
        // ? Diseño del Boton Iniciar
        botonInicio.setBackground(new Color(0x6598a4));
        botonInicio.setForeground(Color.BLACK);
        botonInicio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonInicio.setMargin(new Insets(10, 10, 10, 10));
        // botonInicio.addActionListener(ListeneButtonInicio);
        botonInicio.addMouseListener(EventsButtonInicio);
        // ? Diseño del Boton Finalizar
        botonFinalizar.setBackground(new Color(0x6598a4));
        botonFinalizar.setForeground(Color.BLACK);
        botonFinalizar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonFinalizar.setMargin(new Insets(10, 10, 10, 10));
        // botonFinalizar.addActionListener(ListeneButtonInicio);
        botonFinalizar.addMouseListener(EventsButtonFinalizar);

        // ! Diseño de los CheckBox
        // ? Diseño del CheckBox Automatizado
        automatizado.setFont(new Font("Consolas", Font.BOLD, 14));
        automatizado.setForeground(new Color(0x000000));
        automatizado.setBackground(new Color(0x162524));
        automatizado.setFocusPainted(false);
        automatizado.setOpaque(false);
        // automatizado.addMouseListener(EventsButtonInicio);

        // ? Diseño del CheckBox Manual
        manual.setFont(new Font("Consolas", Font.BOLD, 14));
        manual.setForeground(new Color(0x000000));
        // manual.setBackground(new Color(0x162524));
        manual.setFocusPainted(false);
        manual.setOpaque(false);

        // ! ubicación de los checkBox del menu
        int alturaestados = 100;
        automatizado.setBounds(40, alturaestados, 120, 20);
        automatizado.setFocusable(false);
        manual.setBounds(180, alturaestados, 100, 20);
        manual.setFocusable(false);

        // ! Ubicación de los botones de inicio y finalizacion
        int alturabotones = 60;
        botonInicio.setBounds(40, alturabotones, 80, 20);
        botonInicio.setFocusable(false);
        botonFinalizar.setBounds(160, alturabotones, 100, 20);
        botonFinalizar.setFocusable(false);

        // ! Ubicacion de la etiqueta
        Titulomenu.setBounds(70, 20, 160, 20);
        // ! Implementación de los elementos al panel
        panelmenu.add(Titulomenu);
        panelmenu.add(botonInicio);
        panelmenu.add(botonFinalizar);
        panelmenu.add(automatizado);
        panelmenu.add(manual);
        // panelmenu.add(comunicacion);

        panelmenu.setBorder(borde);
        return panelmenu;
    }
}
