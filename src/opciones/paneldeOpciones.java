package opciones;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;

public class paneldeOpciones extends JPanel {

    public JPanel menu() {
        JPanel panelmenu = new JPanel();
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 2);

        panelmenu.setBounds(550, 50, 200, 80);
        JButton botonInicio = new JButton("Inicio");
        JButton botonFinalizar = new JButton("Finalizar");
        JCheckBox automatizado = new JCheckBox("Autimatico");
        JCheckBox manual = new JCheckBox("Manual");

        automatizado.setBounds(20, 50, 70, 20);
        automatizado.setFocusable(false);
        manual.setBounds(90, 50, 60, 20);
        manual.setFocusable(false);

        botonInicio.setBounds(10, 10, 80, 20);
        botonInicio.setFocusable(false);
        botonFinalizar.setBounds(10, 40, 80, 20);
        botonFinalizar.setFocusable(false);

        panelmenu.setBackground(new Color(0xD3D3D3));

        panelmenu.add(botonInicio);
        panelmenu.add(botonFinalizar);
        panelmenu.add(automatizado);
        panelmenu.add(manual);

        panelmenu.setBorder(borde);
        return panelmenu;
    }
}
