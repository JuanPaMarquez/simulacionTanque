package App;

import javax.swing.*;

import option.paneldeOpciones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Simulación visual de un tanque con una válvula de tres estados y una tubería
 * hacia una casita.
 */
public class TanqueConValvulaVisual extends JPanel implements ActionListener {

    paneldeOpciones llamar = new paneldeOpciones();

    public static boolean isIniciado = true;
    boolean subiendo = true;
    boolean subiendo2 = true;
    int nivelAControlar = 65;
    boolean cableArribaON = true;// cable conectado a valvula llenadp
    boolean cableAbajoON = false;// cable conectado a valvula vaciado
    private int levelAgua = 0;
    private boolean isAutomatic = false;
    private int delay = 6;

    boolean isFlowing = false;
    boolean hasFlowed = false;
    private int aguaDesague = 0;
    private int vacioAguaDesague = 0;
    // Dimensiones del tanque
    private final int ANCHO_TANQUE = 200;
    private final int ALTO_TANQUE = 300;
    private final int MAXIMO_ALTO_TANQUE = 290; // Intervalo de tiempo en milisegundos
    private final int INTERVALO = 1; // Intervalo de tiempo en milisegundos

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
    private int xTuberia = 250+20;
    private int yTuberia = 165;

    public void ImagenPanel() {
        valvulaC = new ImageIcon("src/public/valvulaCerrada.png");
        valvulaA = new ImageIcon("src/public/valvulaAbierta.png");
        casaImg = new ImageIcon("src/public/casa2removebg.png");
    }

    // Estados de la válvula de la Casa
    private enum EstadoValvula {
        ABIERTA, CERRADA
    }

    private EstadoValvula estadoValvulaCasa = EstadoValvula.CERRADA;
    private EstadoValvula estadoValvulaTanque = EstadoValvula.ABIERTA;
    private EstadoValvula estadoValSeguridadTanque = EstadoValvula.ABIERTA;
    private EstadoValvula estadoValSeguridadCasa= EstadoValvula.ABIERTA;


    // Botón de la válvula Casa
    private JButton botonValvulaCasa;
    private JButton botonControlador;

    // Boton de la valvula del tanque
    private JButton botonValvulaTanque;

    public TanqueConValvulaVisual() {

        estadoValvulaTanque = EstadoValvula.CERRADA;
        ImagenPanel();
        // Configura el temporizador que controla el llenado/vaciado del tanque
        this.add(llamar.menu());

        timer = new Timer(INTERVALO, this);
        timer.start(); // Inicia la animación

        // Configura el botón de la válvula
        botonValvulaCasa = new JButton(); // Estado inicial: ABIERTA // Reducir márgenes para mejor ajuste
        botonValvulaCasa.setFocusable(false); // Evitar que el botón tome el foco
        botonValvulaCasa.setBorderPainted(true);
        botonValvulaCasa.setContentAreaFilled(false);
        botonValvulaCasa.setBounds(382, 385, 50, 40);
        botonValvulaCasa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar el estado de la válvula cuando se presiona el botón
                switch (estadoValvulaCasa) {
                    case ABIERTA:
                        estadoValvulaCasa = EstadoValvula.CERRADA;
                        break;

                    case CERRADA:
                        aguaDesague = 0;
                        vacioAguaDesague = 0;
                        estadoValvulaCasa = EstadoValvula.ABIERTA;
                        break;
                }
            }
        });

        botonControlador = new JButton();
        botonControlador.setFocusable(false);
        botonControlador.setForeground(Color.BLACK);
        botonControlador.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        botonControlador.setBorderPainted(true);
        botonControlador.setBounds(60, 200, 50, 50);
        botonControlador.setContentAreaFilled(false);
        botonControlador.setBorderPainted(false);
        botonControlador.setEnabled(false);
        botonControlador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero = 100;
                boolean valido = false;
                while (!valido) {
                    String input = JOptionPane.showInputDialog(null, "¿A que nivel tendras el tanque?:");
                    try {
                        numero = Integer.parseInt(input);
                        if (numero >= 30 && numero <= 100) {
                            valido = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor, introduce un nivel válido entre 50 y 100.");
                        }
                    } catch (NumberFormatException err) {
                        JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, introduce un número.");
                    }
                }
                nivelAControlar = numero;
                repaint();
            }
        });

        botonValvulaTanque = new JButton();
        botonValvulaTanque.setFocusable(false);
        botonValvulaTanque.setForeground(Color.BLACK);
        botonValvulaTanque.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        botonValvulaTanque.setMargin(new Insets(10, 10, 10, 10));
        botonValvulaTanque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (estadoValvulaTanque) {
                    case ABIERTA:
                        estadoValvulaTanque = EstadoValvula.CERRADA;
                        break;

                    case CERRADA:
                        aguaH = 0;
                        vacioAguaH = 0;
                        aguaV = 0;
                        vacioAguaV = 0;
                        estadoValvulaTanque = EstadoValvula.ABIERTA;
                        break;
                }

            }

        });

        // Configura el layout absoluto
        this.setLayout(null);
        // superior izquierda
        botonValvulaTanque.setBounds(xTuberia + 40, yTuberia - 30, 50, 50);
        botonValvulaTanque.setBorderPainted(true);
        botonValvulaTanque.setContentAreaFilled(false);
        this.add(botonValvulaCasa);
        this.add(botonValvulaTanque);
        this.add(botonControlador);
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
        int xTanque = 430;
        int yTanque = 165;

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

        int alturaAgua = (int) ((nivelAgua / 1000.0) * MAXIMO_ALTO_TANQUE); // Nivel proporcional
        levelAgua = (int) ((nivelAgua / 10));

        // System.out.println(alturaAgua);
        hasFlowed = (alturaAgua > 4) ? true : false;

        posicionAgua = yTanque + ALTO_TANQUE - alturaAgua;

        g2d.fillRect(xTanque, posicionAgua, ANCHO_TANQUE, alturaAgua); // Posición del agua

        // Dibujar el porcentaje del nivel de agua
        Font fuenteInformacion = new Font("Consolas", Font.BOLD, 14);
        g2d.setFont(fuenteInformacion);

        g2d.setColor(Color.BLACK);
        DecimalFormat df = new DecimalFormat("#");
        g2d.drawString("Nivel: " + df.format((nivelAgua / 10)) + "%", xTanque + 50, yTanque + ALTO_TANQUE + 30); // Texto
                                                                                                                 // debajo
        // del tanque

        // Agua estatica
        int x1Agua = xTuberia;
        int y1Agua = yTuberia + 5;
        int x2Agua = xTuberia + 80;
        int y2Agua = yTuberia + 5;
        g2d.setStroke(new BasicStroke(5));

        g2d.setColor(Color.BLUE);
        g2d.drawLine(x1Agua, y1Agua, 70+250, y2Agua);
        if (!(vacioAguaH == aguaH && estadoValvulaTanque == EstadoValvula.CERRADA)) {
            g2d.setColor(Color.BLUE);
            g2d.drawLine(x2Agua + vacioAguaH, y1Agua, x2Agua + aguaH, y2Agua);
        }

        int xControler = 50;
        int yControler = 100;
        float[] dashPattern = { 10, 10 };
        // controlador de nivel
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));

        // dibujar linea de nivel
        g2d.setColor(Color.BLACK);
        int delay2 = delay + 10;
        g2d.drawLine(xTanque - 2, yTanque + ALTO_TANQUE - (MAXIMO_ALTO_TANQUE * nivelAControlar / 100) + (delay2),
                xTanque + 202, yTanque + ALTO_TANQUE - (MAXIMO_ALTO_TANQUE * nivelAControlar / 100) + (delay2)); // Linea
                                                                                                          // Derecha
        g2d.drawLine(xTanque - 2, yTanque + ALTO_TANQUE - (MAXIMO_ALTO_TANQUE * nivelAControlar / 100) - (delay2),
                xTanque + 202, yTanque + ALTO_TANQUE - (MAXIMO_ALTO_TANQUE * nivelAControlar / 100) - (delay2)); // Linea
                                                                                                          // Derecha

        g2d.setColor(Color.BLACK);

        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));

        if (cableArribaON == true && isAutomatic) {
            g2d.setColor(Color.getHSBColor(0.25f, 0.8f, 0.8f));
        } else {
            g2d.setColor(Color.BLACK);
        }

        g2d.drawLine(xControler + 35, yControler + 20, xControler + 35, yControler + 100); // Linea Derecha
        if (cableAbajoON && isAutomatic) {
            g2d.setColor(Color.getHSBColor(0.25f, 0.8f, 0.8f));
        } else {
            g2d.setColor(Color.BLACK);
        }
        g2d.drawLine(xControler + 35, yControler + 150, xControler + 35, yControler + 400); // Linea Derecha
        g2d.drawLine(xControler + 35, yControler + 400, xControler + 358, yControler + 400); // Linea Derecha
        g2d.drawLine(xControler + 358, yControler + 400, xControler + 358, yControler + 310); // Linea Derecha

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawOval(xControler + 10, yControler + 100, 50, 50);
        g2d.drawLine(xControler + 60, yControler + 125, xControler + 125, yControler + 125); // Linea Derecha
        g2d.drawString("ST", xControler + 27, yControler + 130);

        g2d.setStroke(new BasicStroke(5));

        // Agua Vertical
        if (aguaH >= 82) {
            g2d.setColor(Color.BLUE);
            caidaAgua = y2Agua + aguaV;
            if (!(vacioAguaV == aguaV && estadoValvulaTanque == EstadoValvula.CERRADA)) {

                g2d.drawLine(x2Agua + aguaH, y2Agua + vacioAguaV, x2Agua + aguaH, caidaAgua);
            }
        }

        if (estadoValvulaTanque == EstadoValvula.ABIERTA) {
            g2d.drawImage(this.valvulaA.getImage(), xTuberia + 40, yTuberia - 30, 50, 50, this);
        } else {
            g2d.drawImage(this.valvulaC.getImage(), xTuberia + 40, yTuberia - 30, 50, 50, this);
        }

        g2d.drawImage(this.casaImg.getImage(), 900, 400, 220, 150, this);
    }

    private void dibujarTuberia(Graphics2D g2d, int xTanque, int yTanque, int anchoTanque, int altoTanque) {
        // Coordenadas iniciales (base del tanque)
        int xInicio = xTanque + anchoTanque + 2;
        int yInicio = yTanque + altoTanque - 8;

        // Coordenadas finales (entrada de la casa)
        int xFinal = xInicio + 100; // 100 píxeles a la derecha

        // agua desague

        g2d.setColor(Color.BLACK);

        // Tuberia parte horizontal
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(xInicio + 50, yInicio, xFinal + 50, yInicio); // Tuberia Línea superior
        g2d.drawLine(xInicio + 50, yInicio + 10, xFinal + 50, yInicio + 10); // Tuberia Línea inferior


         // Tuberia segunda parte a casa
         g2d.setStroke(new BasicStroke(5));
         g2d.drawLine(xInicio + 200, yInicio, xFinal + 200, yInicio); // Tuberia Línea superior
         g2d.drawLine(xInicio + 200, yInicio + 10, xFinal + 200, yInicio + 10); // Tuberia Línea inferior

        if ((vacioAguaDesague == aguaDesague)) {
        } else {
            if (!(vacioAguaDesague == aguaDesague && estadoValvulaCasa == EstadoValvula.CERRADA)) {
                g2d.setColor(Color.BLUE);
                g2d.drawLine(xInicio + 40 + vacioAguaDesague, yInicio + 5, xInicio + 40 + aguaDesague, yInicio + 5);
            }
        }

        if (estadoValvulaCasa == EstadoValvula.ABIERTA) {
            g2d.drawImage(this.valvulaA.getImage(), xInicio, yInicio - 30, 50, 50, this);
        } else {
            g2d.drawImage(this.valvulaC.getImage(), xInicio, yInicio - 30, 50, 50, this);
        }

        if (this.estadoValSeguridadCasa == EstadoValvula.ABIERTA) {
            g2d.drawImage(this.valvulaA.getImage(), xInicio+150, yInicio - 30, 50, 50, this);
        } else {
            g2d.drawImage(this.valvulaC.getImage(), xInicio+150, yInicio - 30, 50, 50, this);
        }



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (llamar.automatizado.isSelected()) {
            isAutomatic = true;
            if (isIniciado) {
                if (subiendo) {
                    estadoValvulaTanque = EstadoValvula.ABIERTA;
                    estadoValvulaCasa = EstadoValvula.CERRADA;
                    vacioAguaH = 0;
                    vacioAguaV = 0;
                }
                if (subiendo2) {
                    estadoValvulaTanque = EstadoValvula.CERRADA;
                    estadoValvulaCasa = EstadoValvula.ABIERTA;
                }
                isIniciado = false;
                this.cableAbajoON = false;
                this.cableArribaON = true;
            }

        } else {
            isIniciado = true;
            this.cableAbajoON = false;
            this.cableArribaON = false;
            isAutomatic = false;
        }

        if (isAutomatic) {
            this.botonControlador.setEnabled(false);
            this.botonValvulaCasa.setEnabled(false);
            this.botonValvulaTanque.setEnabled(false);

            if (levelAgua >= nivelAControlar + delay - 1 && subiendo) {
                this.cableArribaON = false;
                this.cableAbajoON = true;
                subiendo = false;
                estadoValvulaTanque = EstadoValvula.CERRADA;
                estadoValvulaCasa = EstadoValvula.ABIERTA;
                subiendo2 = true;
                aguaDesague = 0;
                vacioAguaDesague = 0;
            }

            if (levelAgua <= nivelAControlar - delay && subiendo2) {
                this.cableArribaON = true;
                this.cableAbajoON = false;
                subiendo2 = false;
                estadoValvulaTanque = EstadoValvula.ABIERTA;
                estadoValvulaCasa = EstadoValvula.CERRADA;
                subiendo = true;
                aguaH = 0;
                vacioAguaH = 0;
                aguaV = 0;
                vacioAguaV = 0;

            }

        } else {
            this.botonValvulaCasa.setEnabled(true);
            this.botonValvulaTanque.setEnabled(true);
        }

        if (llamar.botonInicio.isSelected() == true) {
            this.botonControlador.setEnabled(false);
            if (estadoValvulaTanque == EstadoValvula.ABIERTA) {

                if (aguaH < 82) {

                    aguaH = aguaH + 2;
                } else if (caidaAgua < posicionAgua - 3) {

                    aguaV = aguaV + 2;

                } else if (nivelAgua < 1000 ) {
                    nivelAgua = nivelAgua + 0.4;// variable de control
                    if (nivelAgua > 1000) {
                        nivelAgua = 1000;
                    }
                }

            } else {
                if (vacioAguaH < (aguaH)) {

                    vacioAguaH = vacioAguaH + 2;
                } else if (vacioAguaV < aguaV) {

                    vacioAguaV = vacioAguaV + 2;
                }
            }

            if (estadoValvulaCasa == EstadoValvula.ABIERTA) {
                if (nivelAgua > 0) {
                    nivelAgua = nivelAgua - 0.2;
                } else {
                    nivelAgua = 0;
                }
                if (aguaDesague < 110 && hasFlowed) {
                    aguaDesague = aguaDesague + 1;
                }
                if (!hasFlowed) {
                    if (vacioAguaDesague < (aguaDesague)) {
                        vacioAguaDesague = vacioAguaDesague + 1;
                    }
                }

            } else {
                if (vacioAguaDesague < (aguaDesague)) {
                    vacioAguaDesague = vacioAguaDesague + 1;
                }
            }
            repaint();
        } // Redibujar el tanque con el nuevo nivel de agua
        else {
            botonValvulaCasa.setEnabled(false);
            botonValvulaTanque.setEnabled(false);
            nivelAgua = 0;
            aguaH = 0;
            vacioAguaH = 0;
            aguaV = 0;
            vacioAguaV = 0;
            aguaDesague = 0;
            vacioAguaDesague = 0;
        }
    }
}
