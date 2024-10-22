import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simulación visual de un tanque con una válvula de tres estados y una tubería
 * hacia una casita.
 */
public class TanqueConValvulaVisual extends JPanel implements ActionListener {

    // Dimensiones del tanque
    private static final int ANCHO_TANQUE = 200;
    private static final int ALTO_TANQUE = 300;
    private static final int INTERVALO = 10; // Intervalo de tiempo en milisegundos

    // Nivel de agua en porcentaje
    private int nivelAgua = 0;
    private int aguaH = 0;
    private int aguaV = 0;
    private int posicionAgua;
    private int caidaAgua;

    // Temporizador para controlar el llenado/vaciado del tanque
    private Timer timer;

    private Image imagenValvulaC;
    private Image imagenValvulaA;

    public void menu() {
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
        this.add(panelmenu);
    }

    public void ImagenPanel() {
        // Cargar la imagen usando ImageIcon
        ImageIcon valvulaC = new ImageIcon("src/public/valvulaCerrada.png");
        ImageIcon valvulaA = new ImageIcon("src/public/valvulaAbierta.png");
        // ImageIcon valvulaO = new ImageIcon("public/tanqueAbierta.png");
        imagenValvulaC = valvulaC.getImage(); // Obtener el objeto Image
        imagenValvulaA = valvulaA.getImage(); // Obtener el objeto Image
    }

    // Estados de la válvula
    private enum EstadoValvula {
        ABIERTA, CERRADA
    }

    private EstadoValvula estadoValvula = EstadoValvula.CERRADA;

    // Botón de la válvula
    private JButton botonValvula;

    public TanqueConValvulaVisual() {
        // Configura el temporizador que controla el llenado/vaciado del tanque
        ImagenPanel();
        menu();
        timer = new Timer(INTERVALO, this);
        timer.start(); // Inicia la animación

        // Configura el botón de la válvula
        botonValvula = new JButton("Cerrar Válvula"); // Estado inicial: ABIERTA // Reducir márgenes para mejor ajuste
        botonValvula.setFocusable(false); // Evitar que el botón tome el foco
        botonValvula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar el estado de la válvula cuando se presiona el botón
                switch (estadoValvula) {
                    case ABIERTA:
                        estadoValvula = EstadoValvula.CERRADA;
                        botonValvula.setText("Cerrar Valvula");
                        break;
                    // case MEDIO_ABIERTA:
                    // estadoValvula = EstadoValvula.CERRADA;
                    // botonValvula.setText("Abrir Válvula");
                    // break;
                    case CERRADA:
                        estadoValvula = EstadoValvula.ABIERTA;
                        botonValvula.setText("Abrir Válvula");
                        break;
                }
            }
        });

        // Configura el layout absoluto
        this.setLayout(null);
        // Añadir el botón al panel y establecer su posición fija
        botonValvula.setBounds(400, 400, 140, 30); // Posición fija en la esquina superior izquierda
        this.add(botonValvula);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        

        // Antialiasing para mejor calidad visual
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Coordenadas del tanque
        int xTanque = 180;
        int yTanque = 115;

        // Dibujar el tanque
        // g2d.setColor(Color.GRAY);
        // g2d.drawRect(xTanque, yTanque, ANCHO_TANQUE, ALTO_TANQUE); // Posición y
        // tamaño del tanque
        // Coordenadas de los vértices del triángulo
        int[] xTriangulo = { xTanque - 2, xTanque + 101, xTanque + 202 }; // Coordenadas X de los puntos del triángulo
        int[] yTriangulo = { yTanque, yTanque - 50, yTanque }; // Coordenadas Y de los puntos del triángulo
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillPolygon(xTriangulo, yTriangulo, 3);
        g2d.fillRect(xTanque, yTanque, ANCHO_TANQUE, ALTO_TANQUE); // Posición y tamaño del tanque
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(xTanque - 2, yTanque + 10, xTanque - 2, yTanque + 302); // Linea Izquierda
        g2d.drawLine(xTanque + 202, yTanque, xTanque + 202, yTanque + 302); // Linea Derecha
        g2d.drawLine(xTanque - 2, yTanque + 302, xTanque + 202, yTanque + 302); // Linea Inferior

        g2d.drawPolygon(xTriangulo, yTriangulo, 3);

        // Dibujar la línea horizontal en el medio del tanque
        // g2d.setColor(Color.BLACK);
        // g2d.drawLine(xTanque, yTanque + ALTO_TANQUE / 2, xTanque + ANCHO_TANQUE,
        // yTanque + ALTO_TANQUE / 2);

        // Dibujar el nivel de agua
        g2d.setColor(Color.BLUE);
        int alturaAgua = (int) ((nivelAgua / 1000.0) * ALTO_TANQUE); // Nivel proporcional
        posicionAgua = yTanque + ALTO_TANQUE - alturaAgua;
        g2d.fillRect(xTanque, posicionAgua, ANCHO_TANQUE, alturaAgua); // Posición del agua

        // Dibujar el porcentaje del nivel de agua
        g2d.setColor(Color.BLACK);
        g2d.drawString("Nivel: " + (nivelAgua / 10) + "%", xTanque + 50, yTanque + ALTO_TANQUE + 30); // Texto debajo
                                                                                                      // del tanque

        // Dibujar la tubería
        dibujarTuberia(g2d, xTanque, yTanque, ANCHO_TANQUE, ALTO_TANQUE);

        // dibujarCasa(g2d, xTanque, yTanque, ANCHO_TANQUE, ALTO_TANQUE);

        // Dibujar tuberia

        int xTuberia = 10;
        int yTuberia = 115;

        g2d.setColor(Color.BLACK);
        // Tuberia parte horizontal
        g2d.drawLine(xTuberia, yTuberia, xTuberia + 175, yTuberia); // Tuberia Línea superior
        g2d.drawLine(xTuberia, yTuberia + 10, xTuberia + 170, yTuberia + 10); // Tuberia Línea inferior
        // Tuberia parte vertical
        // g2d.drawLine(xTuberia+170, yTuberia+10, xTuberia+170, yTuberia+30); //
        // Tuberia Bajada izquierda
        // g2d.drawLine(xTuberia+180, yTuberia, xTuberia+180, yTuberia+30); // Tuberia
        // Bajada derecha
        g2d.drawImage(imagenValvulaC, xTanque + 200, yTanque, 50, 50, this);
        g2d.drawImage(imagenValvulaA, xTanque + 250, yTanque, 50, 50, this);

        // Agua estatica
        int x1Agua = xTuberia;
        int y1Agua = yTuberia + 5;
        int x2Agua = xTuberia + 50;
        int y2Agua = yTuberia + 5;

        g2d.setColor(Color.BLUE);
        g2d.drawLine(x1Agua, y1Agua, x2Agua + aguaH, y2Agua);

        // Agua Vertical

        if (aguaH == 125) {
            caidaAgua = y2Agua + aguaV;
            g2d.drawLine(x2Agua + aguaH, y2Agua, x2Agua + aguaH, caidaAgua);
        }

        // tuberia.drawRect(10, 60, 60, 80);
        // tuberia.fillRect(10, 10, 30, 30);

    }

    /**
     * Método para dibujar la tubería desde la base del tanque hasta la casita.
     */
    private void dibujarTuberia(Graphics2D g2d, int xTanque, int yTanque, int anchoTanque, int altoTanque) {
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(5));

        // Coordenadas iniciales (base del tanque)
        int xInicio = xTanque + anchoTanque;
        int yInicio = yTanque + altoTanque;

        // Coordenadas finales (entrada de la casa)
        int xFinal = xInicio + 100; // 100 píxeles a la derecha
        int yFinal = yInicio + 50; // 50 píxeles hacia abajo

        // Dibujar una línea recta que representa la tubería
        g2d.drawLine(xInicio, yInicio, xFinal, yFinal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (aguaH < 125) {
            aguaH++;
        } else if (caidaAgua < posicionAgua - 2) {
            aguaV++;
        } else if (nivelAgua < 1000) {
            nivelAgua++;
        }
        // Controlar el llenado o vaciado según el estado de la válvula
        switch (estadoValvula) {
            case ABIERTA:
                if (nivelAgua > 0) {
                    nivelAgua--; // Llenar el tanque
                }
                break;
            case CERRADA:

                break;
        }
        repaint(); // Redibujar el tanque con el nuevo nivel de agua
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                JFrame frame = new JFrame("Simulación de Tanque con Válvula y Casita");
                TanqueConValvulaVisual panel = new TanqueConValvulaVisual();
                frame.add(panel);
                frame.setSize(800, 600); // Aumentar el tamaño para acomodar la tubería y la casa
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null); // Centrar la ventana
                frame.setVisible(true);
            }
        });
    }
}
