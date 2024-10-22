package opciones;

import javax.swing.*;

import java.awt.*;
import javax.swing.border.Border;
// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;

public class paneldeOpciones extends JPanel {

    public JPanel menu() {

        // Creación y customización del panel
        JPanel panelmenu = new JPanel();
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 5);
        panelmenu.setBackground(new Color(0xD3D3D3));
        panelmenu.setLayout(null);

        // Ubicacion del panel en la vetana principal
        int ubicacionX = 450;
        int ubicacionY = 50;
        panelmenu.setBounds(ubicacionX, ubicacionY, 300, 100);

        ButtonGroup comunicacion = new ButtonGroup();
        // Creacion de los elementos del menu
        JButton botonInicio = new JButton("Inicio");
        JButton botonFinalizar = new JButton("Finalizar");
        JCheckBox automatizado = new JCheckBox("Autimatico");
        JCheckBox manual = new JCheckBox("Manual");

        comunicacion.add(automatizado);
        comunicacion.add(manual);

        // ! Diseño de los CheckBox
        automatizado.setFont(new Font("Arial", Font.BOLD, 14));
        automatizado.setForeground(new Color(0x000000));
        // automatizado.setBackground(new Color(0x162524));
        automatizado.setFocusPainted(false);
        automatizado.setOpaque(false);

        manual.setFont(new Font("Arial", Font.BOLD, 14));
        manual.setForeground(new Color(0x000000));
        // manual.setBackground(new Color(0x162524));
        manual.setFocusPainted(false);
        manual.setOpaque(false);

        // ubicación de los checkBox del menu
        int alturaestados = 60;
        automatizado.setBounds(40, alturaestados, 100, 20);
        automatizado.setFocusable(false);
        manual.setBounds(160, alturaestados, 100, 20);
        manual.setFocusable(false);

        // Ubicación de los botones de inicio y finalizacion
        int alturabotones = 20;
        botonInicio.setBounds(40, alturabotones, 80, 20);
        botonInicio.setFocusable(false);
        botonFinalizar.setBounds(160, alturabotones, 100, 20);
        botonFinalizar.setFocusable(false);

        // Implementación de los elementos al panel
        panelmenu.add(botonInicio);
        panelmenu.add(botonFinalizar);
        panelmenu.add(automatizado);
        panelmenu.add(manual);
        // panelmenu.add(comunicacion);

        panelmenu.setBorder(borde);
        return panelmenu;
    }
}
