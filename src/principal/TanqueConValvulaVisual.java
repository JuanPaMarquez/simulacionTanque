package principal;

import javax.swing.*;

import opciones.RoundedButton;
import opciones.paneldeOpciones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.awt.event.MouseEvent;

/**
 * Simulación visual de un tanque con una válvula de tres estados y una tubería
 * hacia una casita.
 */
public class TanqueConValvulaVisual extends JPanel implements ActionListener {

    paneldeOpciones llamar = new paneldeOpciones();

    boolean isFlowing = false;
    boolean hasFlowed = false;
    private int aguaDesague = 0;
    private int vacioAguaDesague = 0;
    // Dimensiones del tanque
    private static final int ANCHO_TANQUE = 200;
    private static final int ALTO_TANQUE = 300;
    private static final int INTERVALO = 10; // Intervalo de tiempo en milisegundos

    // Nivel de agua en porcentaje
    private double nivelAgua = 0;
    private int aguaH = 0;
    private int vacioAguaH = 0;
    private int aguaV = 0;
    private int vacioAguaV = 0;
    private int posicionAgua;
    private int caidaAgua;

    // Temporizador para controlar el llenado/vaciado del tanque
    private Timer timer;
    private ImageIcon valvulaC;
    private ImageIcon valvulaA;
    private ImageIcon casaImg;

    // dimensiones de tuberia
    private int xTuberia = 20;
    private int yTuberia = 115;

    public void ImagenPanel() {
        valvulaC = new ImageIcon("src/public/valvulaCerrada.png");
        valvulaA = new ImageIcon("src/public/valvulaAbierta.png");
        casaImg = new ImageIcon("src/public/casa2removebg.png");

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
        estadoValvulaTanque = estadoValvulaTanque.CERRADA;
        ImagenPanel();
        // Configura el temporizador que controla el llenado/vaciado del tanque
        this.add(llamar.menu());

        timer = new Timer(INTERVALO, this);
        timer.start(); // Inicia la animación

        // Configura el botón de la válvula

        botonValvulaCasa = new JButton(); // Estado inicial: ABIERTA // Reducir márgenes para mejor ajuste

        botonValvulaCasa.setFocusable(false); // Evitar que el botón tome el foco
        botonValvulaCasa.setBorderPainted(false);
        botonValvulaCasa.setContentAreaFilled(false);
        botonValvulaCasa.setBounds(382, 385, 50, 40);
        botonValvulaCasa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar el estado de la válvula cuando se presiona el botón
                switch (estadoValvulaCasa) {
                    case ABIERTA:
                        estadoValvulaCasa = EstadoValvulaCasa.CERRADA;

                        break;

                    case CERRADA:
                        aguaDesague = 0;
                        vacioAguaDesague = 0;

                        estadoValvulaCasa = EstadoValvulaCasa.ABIERTA;

                        break;
                }
            }
        });

        botonValvulaTanque = new JButton();
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

                        break;
                    case CERRADA:

                        aguaH = 0;
                        vacioAguaH = 0;
                        aguaV = 0;
                        vacioAguaV = 0;
                        estadoValvulaTanque = EstadoValvulaTanque.ABIERTA;

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
        // superior izquierda
        botonValvulaTanque.setBounds(xTuberia + 40, yTuberia - 30, 50, 50);
        botonValvulaTanque.setBorderPainted(false);
        botonValvulaTanque.setContentAreaFilled(false);
        this.add(botonValvulaCasa);
        this.add(botonValvulaTanque);
    }

    protected void paintComponentcuadricula(Graphics g1) {
        super.paintComponent(g1); // Llama a la implementación del padre para pintar el fondo correctamente

        // Definir el ancho y el alto de las celdas de la cuadrícula
        int anchoCelda = 10;
        int altoCelda = 10;

        // Obtener el ancho y alto del panel
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Dibujar líneas verticales
        int j = 0;
        for (int x = 0; x < anchoPanel; x += anchoCelda) {
            if (j % 5 == 0) {
                g1.setColor(Color.LIGHT_GRAY);
                g1.drawLine(x, 0, x, altoPanel);
            }

            j++;
        }
        j = 0;
        // Dibujar líneas horizontales
        for (int y = 0; y < altoPanel; y += altoCelda) {
            if (j % 5 == 0) {
                g1.setColor(Color.LIGHT_GRAY);
                g1.drawLine(0, y, anchoPanel, y);
            }
            j++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paintComponentcuadricula(g2d);

        // Antialiasing para mejor calidad visual
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Coordenadas del tanque
        int xTanque = 180;
        int yTanque = 115;

        // Dibujar el tanque
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

        // Dibujar la tubería
        dibujarTuberia(g2d, xTanque, yTanque, ANCHO_TANQUE, ALTO_TANQUE);
        // Dibujar tuberia

        g2d.setColor(Color.BLACK);
        // Tuberia parte horizontal

        g2d.drawLine(xTuberia, yTuberia, xTuberia + 50, yTuberia); // Tuberia Línea superior
        g2d.drawLine(xTuberia, yTuberia + 10, xTuberia + 50, yTuberia + 10); // Tuberia Línea inferior

        // Tuberia parte horizontal
        g2d.drawLine(xTuberia + 80, yTuberia, xTuberia + 163, yTuberia); // Tuberia Línea superior
        g2d.drawLine(xTuberia + 80, yTuberia + 10, xTuberia + 158, yTuberia + 10); // Tuberia Línea inferior

        // Dibujar el nivel de agua
        g2d.setColor(Color.BLUE);
        int alturaAgua = (int) ((nivelAgua / 1000.0) * ALTO_TANQUE); // Nivel proporcional
        hasFlowed = (alturaAgua > 4) ? true : false;
        posicionAgua = yTanque + ALTO_TANQUE - alturaAgua;
        g2d.fillRect(xTanque, posicionAgua, ANCHO_TANQUE, alturaAgua); // Posición del agua

        // Dibujar el porcentaje del nivel de agua
        Font fuenteInformacion = new Font("Consolas", Font.BOLD, 14);
        g2d.setFont(fuenteInformacion);

        g2d.setColor(Color.BLACK);
        DecimalFormat df = new DecimalFormat("#.00");
        g2d.drawString("Nivel: " + df.format((nivelAgua / 10)) + "%", xTanque + 50, yTanque + ALTO_TANQUE + 30); // Texto
                                                                                                                 // debajo
        // del tanque

        // Agua estatica
        int x1Agua = xTuberia;
        int y1Agua = yTuberia + 5;
        int x2Agua = xTuberia + 80;
        int y2Agua = yTuberia + 5;

        g2d.setColor(Color.BLUE);
        g2d.drawLine(x1Agua, y1Agua, 70, y2Agua);
        if (!(vacioAguaH == aguaH && estadoValvulaTanque == EstadoValvulaTanque.CERRADA)) {
            g2d.setColor(Color.BLUE);
            g2d.drawLine(x2Agua + vacioAguaH, y1Agua, x2Agua + aguaH, y2Agua);
        }

        // Agua Vertical
        if (aguaH >= 82) {
            g2d.setColor(Color.BLUE);
            caidaAgua = y2Agua + aguaV;
            if (!(vacioAguaV == aguaV && estadoValvulaTanque == EstadoValvulaTanque.CERRADA)) {

                g2d.drawLine(x2Agua + aguaH, y2Agua + vacioAguaV, x2Agua + aguaH, caidaAgua);
            }
        }

        if (estadoValvulaTanque == EstadoValvulaTanque.ABIERTA) {
            g2d.drawImage(this.valvulaA.getImage(), xTuberia + 40, yTuberia - 30, 50, 50, this);
        } else {
            g2d.drawImage(this.valvulaC.getImage(), xTuberia + 40, yTuberia - 30, 50, 50, this);
        }

        g2d.drawImage(this.casaImg.getImage(), 500, 350, 220, 150, this);
    }

    /**
     * Método para dibujar la tubería desde la base del tanque hasta la casita.
     */
    private void dibujarTuberia(Graphics2D g2d, int xTanque, int yTanque, int anchoTanque, int altoTanque) {
        // Coordenadas iniciales (base del tanque)
        int xInicio = xTanque + anchoTanque + 2;
        int yInicio = yTanque + altoTanque - 8;

        // Coordenadas finales (entrada de la casa)
        int xFinal = xInicio + 100; // 100 píxeles a la derecha
        int yFinal = yInicio + 50; // 50 píxeles hacia abajo

        // agua desague

        g2d.setColor(Color.BLACK);

        // Tuberia parte horizontal
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(xInicio + 50, yInicio, xFinal + 50, yInicio); // Tuberia Línea superior
        g2d.drawLine(xInicio + 50, yInicio + 10, xFinal + 50, yInicio + 10); // Tuberia Línea inferior

        if (!(vacioAguaDesague == aguaDesague)) {

            if (!(vacioAguaDesague == aguaDesague && estadoValvulaCasa == EstadoValvulaCasa.CERRADA)) {
                g2d.setColor(Color.BLUE);

                g2d.drawLine(xInicio + 40 + vacioAguaDesague, yInicio + 5, xInicio + 40 + aguaDesague, yInicio + 5);
            }
        }

        if (estadoValvulaCasa == EstadoValvulaCasa.ABIERTA) {
            g2d.drawImage(this.valvulaA.getImage(), xInicio, yInicio - 30, 50, 50, this);
        } else {
            g2d.drawImage(this.valvulaC.getImage(), xInicio, yInicio - 30, 50, 50, this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (llamar.botonInicio.isSelected() == true) {
            if (estadoValvulaTanque == EstadoValvulaTanque.ABIERTA) {

                if (aguaH < 82) {

                    aguaH = aguaH + 2;
                } else if (caidaAgua < posicionAgua - 3) {

                    aguaV = aguaV + 2;

                } else if (nivelAgua < 970) {

                    // System.out.println("llenado"+estadoValvula);
                    nivelAgua = nivelAgua + 0.4;// variable de control
                }

            } else {
                if (vacioAguaH < (aguaH)) {

                    vacioAguaH = vacioAguaH + 2;
                } else if (vacioAguaV < aguaV) {

                    vacioAguaV = vacioAguaV + 2;
                }
            }

            if (estadoValvulaCasa == EstadoValvulaCasa.ABIERTA) {
                if (nivelAgua > 0) {

                    // System.out.println("llenado"+estadoValvula);
                    nivelAgua = nivelAgua - 0.2;
                } else {
                    nivelAgua = 0;
                }
                // nivelAgua=nivelAgua-0.2;

                if (aguaDesague < 110 && hasFlowed) {
                    aguaDesague = aguaDesague + 2;
                }
                if (!hasFlowed) {
                    if (vacioAguaDesague < (aguaDesague)) {

                        vacioAguaDesague = vacioAguaDesague + 1;
                    }
                }

            } else {
                if (vacioAguaDesague <= (aguaDesague)) {

                    vacioAguaDesague = vacioAguaDesague + 1;
                }
            }
            // Controlar el llenado o vaciado según el estado de la válvula

            switch (estadoValvulaCasa) {

                case ABIERTA:
                    if (nivelAgua > 0) {

                        // nivelAgua--; // vaciado el tanque
                    }
                    break;
                case CERRADA:

                    break;
            }
            repaint();
        } // Redibujar el tanque con el nuevo nivel de agua
        else {
            nivelAgua = 0;
            aguaH = 0;
            vacioAguaH = 0;
            aguaV = 0;
            vacioAguaV = 0;
            aguaDesague = 0;
            vacioAguaDesague = 0;
        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            // Variables de posición para el arrastre de la ventana
            int posX = 0;
            int posY = 0;

            public void run() {

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
                JPanel barraTitulo = new JPanel();
                barraTitulo.setBackground(new Color(44, 62, 80));
                barraTitulo.setPreferredSize(new Dimension(400, 40));
                barraTitulo.setLayout(new BorderLayout());

                // Crear botones para minimizar, maximizar y cerrar
                RoundedButton btnMinimizar = new RoundedButton("_", frame, true);

                RoundedButton btnCerrar = new RoundedButton("X", frame, false);
                JLabel title = new JLabel(" Simulación de Tanque con Válvula y Casita");
                title.setForeground(Color.WHITE);
                title.setFont(new Font("times new roman", Font.BOLD, 20));
                // Acción del botón Cerrar

                // Añadir botones a la barra de título personalizada
                barraTitulo.add(title, BorderLayout.WEST);
                // Crear un panel para los botones
                JPanel panelBotones = new JPanel();
                panelBotones.setBounds(0, 0, 120, 30);
                panelBotones.setBackground(new Color(44, 62, 80));
                panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
                panelBotones.add(btnMinimizar);
                panelBotones.add(btnCerrar);

                barraTitulo.add(panelBotones, BorderLayout.EAST);
                // Añadir el panel de barra de título al JFrame
                frame.add(barraTitulo, BorderLayout.NORTH);

                // Hacer la barra de título arrastrable para mover la ventana
                barraTitulo.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        posX = e.getX();
                        posY = e.getY();
                    }
                });

                barraTitulo.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        frame.setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
                    }
                });
                frame.setVisible(true);
            }
        });
    }
}
