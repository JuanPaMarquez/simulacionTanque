import javax.swing.*;

import opciones.paneldeOpciones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

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

    private ImageIcon valvue = new ImageIcon("src/public/valvulaCerrada.png");
    private JLabel imageValve = new JLabel();

    public void ImagenPanel() {
        // Cargar la imagen usando ImageIcon

        // JLabel imageLabelA = new JLabel(new
        // ImageIcon("src/public/valvulaAbierta.png"));
        imageValve.setBounds(50, 80, 60, 60);
        imageValve.setIcon(new ImageIcon(valvue.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        this.add(imageValve);

    }

    // Estados de la válvula de la Casa
    private enum EstadoValvulaCasa {
        ABIERTA, CERRADA
    }

    private EstadoValvulaCasa estadoValvulaCasa = EstadoValvulaCasa.CERRADA;

    // Estados de la valcula del tanque
    private enum EstadoValvulaTanque {
        ABIERTA, CERRADA
    }

    private EstadoValvulaTanque estadoValvulaTanque = EstadoValvulaTanque.ABIERTA;

    // Botón de la válvula Casa
    private JButton botonValvulaCasa;

    // Boton de la valvula del tanque
    private JButton botonValvulaTanque;

    public TanqueConValvulaVisual() {
        // Configura el temporizador que controla el llenado/vaciado del tanque
        ImagenPanel();
        paneldeOpciones llamar = new paneldeOpciones();
        this.add(llamar.menu());
        timer = new Timer(INTERVALO, this);
        timer.start(); // Inicia la animación

        // Configura el botón de la válvula
        botonValvulaCasa = new JButton("Cerrar Válvula"); // Estado inicial: ABIERTA // Reducir márgenes para mejor
                                                          // ajuste
        botonValvulaCasa.setFocusable(false); // Evitar que el botón tome el foco
        botonValvulaCasa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar el estado de la válvula cuando se presiona el botón
                switch (estadoValvulaCasa) {
                    case ABIERTA:
                        estadoValvulaCasa = EstadoValvulaCasa.CERRADA;
                        botonValvulaCasa.setText("Cerrar Válvula");
                        break;
                    // case MEDIO_ABIERTA:
                    // estadoValvula = EstadoValvula.CERRADA;
                    // botonValvula.setText("Abrir Válvula");
                    // break;
                    case CERRADA:
                        estadoValvulaCasa = EstadoValvulaCasa.ABIERTA;
                        botonValvulaCasa.setText("Abrir Válvula");
                        break;
                }
            }
        });

        botonValvulaTanque = new JButton("Abrir Válvula");
        botonValvulaTanque.setFocusable(false);
        botonValvulaTanque.setForeground(Color.BLACK);
        botonValvulaTanque.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        botonValvulaTanque.setMargin(new Insets(10, 10, 10, 10));
        botonValvulaTanque.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (estadoValvulaTanque) {
                    case ABIERTA:
                        estadoValvulaTanque = EstadoValvulaTanque.CERRADA;
                        botonValvulaTanque.setText("Cerrar Válvula");
                        valvue = new ImageIcon("src/public/valvulaAbierta.png");
                        imageValve.setIcon(
                                new ImageIcon(valvue.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
                        break;
                    // case MEDIO_ABIERTA:
                    // estadoValvula = EstadoValvula.CERRADA;
                    // botonValvula.setText("Abrir Válvula");
                    // break;
                    case CERRADA:
                        estadoValvulaTanque = EstadoValvulaTanque.ABIERTA;
                        botonValvulaTanque.setText("Abrir Válvula");
                        valvue = new ImageIcon("src/public/valvulaCerrada.png");
                        imageValve.setIcon(
                                new ImageIcon(valvue.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
                        break;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botonValvulaTanque.setBackground(new Color(0x2ff238));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonValvulaTanque.setBackground(Color.WHITE);
            }

        });

        // Configura el layout absoluto
        this.setLayout(null);
        // Añadir el botón al panel y establecer su posición fija
        botonValvulaCasa.setBounds(400, 400, 140, 30); // Posición fija en la esquina superior izquierda
        botonValvulaTanque.setBounds(25, 30, 120, 30);
        this.add(botonValvulaCasa);
        this.add(botonValvulaTanque);
    }

    protected void paintComponentcuadricula(Graphics g1) {
        super.paintComponent(g1); // Llama a la implementación del padre para pintar el fondo correctamente

        // Definir el ancho y el alto de las celdas de la cuadrícula
        int anchoCelda = 50;
        int altoCelda = 50;

        // Definir el color de la cuadrícula
        g1.setColor(Color.LIGHT_GRAY);

        // Obtener el ancho y alto del panel
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Dibujar líneas verticales
        for (int x = 0; x < anchoPanel; x += anchoCelda) {
            g1.drawLine(x, 0, x, altoPanel);
        }

        // Dibujar líneas horizontales
        for (int y = 0; y < altoPanel; y += altoCelda) {
            g1.drawLine(0, y, anchoPanel, y);
        }
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
        // g2d.drawImage(imagenValvula, xTanque - 130, yTanque, 50, 50, this);
        // g2d.drawImage(imagenValvulaA, xTanque - 130, yTanque - 30, 50, 50, this);

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
        switch (estadoValvulaCasa) {
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
                panel.setBackground(new Color(0xd4ebb2));
                frame.add(panel);
                frame.setSize(800, 600); // Aumentar el tamaño para acomodar la tubería y la casa
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null); // Centrar la ventana
                frame.setVisible(true);
            }
        });
    }
}
