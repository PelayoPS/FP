package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import logica.Liga;

/**
 * Ventana principal de la aplicación que muestra botones para acceder a las
 * diferentes entidades: Equipos, Campeones y Partidas.
 * Esta clase actúa como punto de entrada principal para la aplicación,
 * permitiendo navegar a las distintas funcionalidades.
 * 
 * @author PelayoPS
 * @version 1.0
 */
public class MainFrame extends JFrame {

    /** Instancia de Liga que contiene todos los datos de la aplicación */
    private Liga liga;

    // Componentes de la interfaz
    /** Botón para acceder a la gestión de equipos */
    private JButton btnEquipos;

    /** Botón para acceder a la gestión de campeones */
    private JButton btnCampeones;

    /** Botón para acceder a la gestión de partidas */
    private JButton btnPartidas;

    /** Etiqueta para el título de la aplicación */
    private JLabel lblTitulo;

    /**
     * Constructor de la clase MainFrame.
     * Inicializa la ventana principal, carga los datos y configura los componentes.
     */
    public MainFrame() {
        // Cargar los datos desde el XML
        liga = new Liga("datos.xml", "ficheros/database.xml");

        // Configurar la ventana
        setTitle("League of Legends - Gestor de Datos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear y configurar los componentes
        inicializarComponentes();

        // Escuchar el cierre de la ventana para guardar los cambios
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                liga.guardarXML();
            }
        });
    }

    /**
     * Inicializa y configura todos los componentes de la interfaz gráfica.
     * Crea los paneles, botones y etiquetas necesarios para la ventana principal.
     */
    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel superior con título
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblTitulo = new JLabel("SISTEMA DE GESTIÓN - LEAGUE OF LEGENDS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(0, 102, 204));
        panelTitulo.add(lblTitulo);

        // Panel central con los botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 20));

        btnEquipos = crearBoton("Gestión de Equipos", new Color(51, 153, 255));
        btnEquipos.setForeground(Color.BLACK);
        btnEquipos.addActionListener(e -> mostrarVentanaEquipos());

        btnCampeones = crearBoton("Gestión de Campeones", new Color(102, 204, 0));
        btnCampeones.addActionListener(e -> mostrarVentanaCampeones());
        btnCampeones.setForeground(Color.BLACK);

        btnPartidas = crearBoton("Gestión de Partidas", new Color(255, 102, 102));
        btnPartidas.addActionListener(e -> mostrarVentanaPartidas());
        btnPartidas.setForeground(Color.BLACK);

        panelBotones.add(btnEquipos);
        panelBotones.add(btnCampeones);
        panelBotones.add(btnPartidas);

        // Añadir paneles al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        // Añadir logo o imagen en la parte inferior
        JLabel lblLogo = new JLabel("© 2025 - Sistema XML de League of Legends", JLabel.CENTER);
        lblLogo.setFont(new Font("Arial", Font.ITALIC, 12));
        panelPrincipal.add(lblLogo, BorderLayout.SOUTH);

        // Establecer el panel principal
        setContentPane(panelPrincipal);
    }

    /**
     * Crea un botón personalizado con un texto y color específicos.
     * 
     * @param texto Texto a mostrar en el botón
     * @param color Color de fondo del botón
     * @return Un botón configurado con el estilo adecuado
     */
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    /**
     * Muestra la ventana de gestión de equipos.
     * Crea una nueva instancia de EquiposFrame y la hace visible.
     */
    private void mostrarVentanaEquipos() {
        EquiposFrame ventana = new EquiposFrame(liga);
        ventana.setVisible(true);
    }

    /**
     * Muestra la ventana de gestión de campeones.
     * Crea una nueva instancia de CampeonesFrame y la hace visible.
     */
    private void mostrarVentanaCampeones() {
        CampeonesFrame ventana = new CampeonesFrame(liga);
        ventana.setVisible(true);
    }

    /**
     * Muestra la ventana de gestión de partidas.
     * Crea una nueva instancia de PartidasFrame y la hace visible.
     */
    private void mostrarVentanaPartidas() {
        PartidasFrame ventana = new PartidasFrame(liga);
        ventana.setVisible(true);
    }

    /**
     * Método principal que inicia la aplicación.
     * Configura el look and feel del sistema y crea la ventana principal.
     * 
     * @param args Argumentos de línea de comando (no utilizados)
     */
    public static void main(String[] args) {
        try {
            // Establecer look and feel del sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}