package option;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

import App.TanqueConValvulaVisual;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class paneldeOpciones extends JPanel {

    public JButton botonInicio = new JButton("Iniciar");
    JButton botonFinalizar = new JButton("Finalizar");
    public JCheckBox automatizado = new JCheckBox("Automatico");
    JCheckBox manual = new JCheckBox("Manual", true);

    public JPanel menu() {

        // ! Creación y customización del panel
        JPanel panelmenu = new JPanel();
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 5);
        panelmenu.setBackground(new Color(0xD3D3D3));
        panelmenu.setLayout(null);

        // ! Ubicacion del panel en la vetana principal
        int ubicacionX = 25;
        int ubicacionY = 25;
        panelmenu.setBounds(ubicacionX, ubicacionY, 250, 150);

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
                TanqueConValvulaVisual.isIniciado = true;
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

        // ! Diseño de los Button
        // ? Diseño del Boton Iniciar
        botonInicio.setBackground(new Color(0x6598a4));
        botonInicio.setForeground(Color.BLACK);
        botonInicio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonInicio.setMargin(new Insets(10, 10, 10, 10));
        botonInicio.addMouseListener(EventsButtonInicio);
        // ? Diseño del Boton Finalizar
        botonFinalizar.setBackground(new Color(0x6598a4));
        botonFinalizar.setForeground(Color.BLACK);
        botonFinalizar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonFinalizar.setMargin(new Insets(10, 10, 10, 10));
        botonFinalizar.addMouseListener(EventsButtonFinalizar);

        // ! Diseño de los CheckBox
        // ? Diseño del CheckBox Automatizado
        automatizado.setFont(new Font("Consolas", Font.BOLD, 14));
        automatizado.setForeground(new Color(0x000000));
        automatizado.setBackground(new Color(0x162524));
        automatizado.setFocusPainted(false);
        automatizado.setOpaque(false);

        // ? Diseño del CheckBox Manual
        manual.setFont(new Font("Consolas", Font.BOLD, 14));
        manual.setForeground(new Color(0x000000));
        manual.setFocusPainted(false);
        manual.setOpaque(false);

        // ! ubicación de los checkBox del menu
        int alturaestados = 100;
        automatizado.setBounds(135, alturaestados, 120, 20);
        automatizado.setFocusable(false);
        manual.setBounds(15, alturaestados, 80, 20);
        manual.setFocusable(false);

        // ! Ubicación de los botones de inicio y finalizacion
        int alturabotones = 60;
        botonInicio.setBounds(15, alturabotones, 80, 20);
        botonInicio.setFocusable(false);
        botonFinalizar.setBounds(135, alturabotones, 100, 20);
        botonFinalizar.setFocusable(false);

        // ! Ubicacion de la etiqueta
        Titulomenu.setBounds(45, 20, 160, 20);
        // ! Implementación de los elementos al panel
        panelmenu.add(Titulomenu);
        panelmenu.add(botonInicio);
        panelmenu.add(botonFinalizar);
        panelmenu.add(automatizado);
        panelmenu.add(manual);

        panelmenu.setBorder(borde);
        return panelmenu;
    }
}
