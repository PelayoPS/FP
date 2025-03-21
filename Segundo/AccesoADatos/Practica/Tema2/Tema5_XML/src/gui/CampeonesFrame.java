package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import logica.Liga;
import modelo.Campeon;

/**
 * Ventana para visualizar los campeones de League of Legends.
 * Esta clase permite mostrar información detallada sobre los campeones
 * disponibles en el juego, incluyendo sus estadísticas, rol y disponibilidad.
 * 
 * @author PelayoPS
 * @version 1.0
 */
public class CampeonesFrame extends JFrame {

    /** Instancia de Liga que contiene los datos de los campeones */
    private Liga liga;

    /** Lista de campeones a mostrar en la interfaz */
    private List<Campeon> campeones;

    // Componentes de la interfaz
    /** Combo box para seleccionar campeones */
    private JComboBox<String> cmbCampeones;

    /** Panel para mostrar las diferentes tarjetas de campeones */
    private JPanel panelTarjetas;

    /** Layout para gestionar las tarjetas de campeones */
    private CardLayout layoutTarjetas;

    /** Etiqueta para mostrar la imagen del campeón */
    private JLabel lblImagen;

    /** Barra de progreso para mostrar el poder del campeón */
    private JProgressBar barPoder;

    /** Barra de progreso para mostrar la dificultad del campeón */
    private JProgressBar barDificultad;

    /**
     * Constructor de la clase CampeonesFrame.
     * Inicializa la ventana y configura los componentes para mostrar
     * la información de los campeones.
     *
     * @param liga Liga que contiene los campeones a visualizar
     */
    public CampeonesFrame(Liga liga) {
        this.liga = liga;
        this.campeones = liga.getCampeones();

        // Configurar la ventana
        setTitle("Visualizador de Campeones");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear y configurar los componentes
        inicializarComponentes();
        cargarDatos();
    }

    /**
     * Inicializa y configura todos los componentes de la interfaz gráfica.
     * Crea el panel principal, títulos, selector de campeones y el panel de
     * tarjetas.
     */
    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel superior con título
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitulo = new JLabel("CAMPEONES DE LEAGUE OF LEGENDS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(102, 204, 0));
        panelTitulo.add(lblTitulo);

        // Panel selector con combo box
        JPanel panelSelector = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblSeleccionar = new JLabel("Seleccionar campeón:");
        lblSeleccionar.setFont(new Font("Arial", Font.BOLD, 14));

        cmbCampeones = new JComboBox<>();
        cmbCampeones.setPreferredSize(new Dimension(200, 30));
        cmbCampeones.addActionListener(e -> mostrarCampeonSeleccionado());

        panelSelector.add(lblSeleccionar);
        panelSelector.add(cmbCampeones);

        // Panel de tarjetas para mostrar los detalles de cada campeón
        layoutTarjetas = new CardLayout();
        panelTarjetas = new JPanel(layoutTarjetas);
        panelTarjetas.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Añadir componentes al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelSelector, BorderLayout.NORTH, 1);
        panelPrincipal.add(panelTarjetas, BorderLayout.CENTER);

        // Establecer el panel principal
        setContentPane(panelPrincipal);
    }

    /**
     * Carga los datos de los campeones en el combo box y crea
     * las tarjetas de información para cada uno de ellos.
     */
    private void cargarDatos() {
        // Limpiar combobox
        cmbCampeones.removeAllItems();

        // Añadir cada campeón al combo y crear su tarjeta
        for (Campeon campeon : campeones) {
            cmbCampeones.addItem(campeon.getNombre());
            crearTarjetaCampeon(campeon);
        }
    }

    /**
     * Crea una tarjeta para mostrar la información detallada de un campeón.
     * 
     * @param campeon El campeón cuya información se mostrará en la tarjeta
     */
    private void crearTarjetaCampeon(Campeon campeon) {
        // Panel principal de la tarjeta con colores según el rol
        Color colorFondo = getColorPorRol(campeon.getRol());

        JPanel panelCampeon = new JPanel(new BorderLayout(15, 15));
        panelCampeon.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCampeon.setBackground(new Color(colorFondo.getRed(), colorFondo.getGreen(), colorFondo.getBlue(), 30));

        // Panel superior con nombre y rol
        JPanel panelInfo = new JPanel(new GridLayout(2, 1));
        panelInfo.setOpaque(false);

        JLabel lblNombre = new JLabel(campeon.getNombre(), JLabel.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 24));
        lblNombre.setForeground(colorFondo);

        JLabel lblRol = new JLabel("Rol: " + campeon.getRol(), JLabel.CENTER);
        lblRol.setFont(new Font("Arial", Font.ITALIC, 16));

        panelInfo.add(lblNombre);
        panelInfo.add(lblRol);

        // Panel central con imagen y estadísticas
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 15, 0));
        panelCentral.setOpaque(false);

        // Panel para imagen (simulada con un panel de color)
        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.setOpaque(false);

        JPanel imagenSimulada = new JPanel();
        imagenSimulada.setBackground(colorFondo);
        imagenSimulada.setPreferredSize(new Dimension(200, 200));

        JLabel lblNombreImagen = new JLabel(campeon.getNombre(), JLabel.CENTER);
        lblNombreImagen.setFont(new Font("Arial", Font.BOLD, 18));
        lblNombreImagen.setForeground(Color.WHITE);
        imagenSimulada.add(lblNombreImagen);

        panelImagen.add(imagenSimulada, BorderLayout.CENTER);

        // Panel para estadísticas
        JPanel panelEstadisticas = new JPanel(new GridLayout(4, 1, 0, 10));
        panelEstadisticas.setOpaque(false);
        panelEstadisticas.setBorder(BorderFactory.createTitledBorder("Estadísticas"));

        // Dificultad
        JPanel panelDificultad = new JPanel(new BorderLayout(5, 0));
        panelDificultad.setOpaque(false);
        JLabel lblDificultad = new JLabel("Dificultad: ");
        JProgressBar barDificultad = new JProgressBar(0, 10);
        barDificultad.setValue(campeon.getDificultad());
        barDificultad.setStringPainted(true);
        barDificultad.setForeground(new Color(255, 102, 102));
        panelDificultad.add(lblDificultad, BorderLayout.WEST);
        panelDificultad.add(barDificultad, BorderLayout.CENTER);

        // Poder
        JPanel panelPoder = new JPanel(new BorderLayout(5, 0));
        panelPoder.setOpaque(false);
        JLabel lblPoder = new JLabel("Poder: ");
        JProgressBar barPoder = new JProgressBar(0, 100);
        barPoder.setValue((int) campeon.getPoder());
        barPoder.setStringPainted(true);
        barPoder.setForeground(new Color(51, 153, 255));
        panelPoder.add(lblPoder, BorderLayout.WEST);
        panelPoder.add(barPoder, BorderLayout.CENTER);

        // Disponibilidad
        JPanel panelDisponible = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDisponible.setOpaque(false);
        JCheckBox chkDisponible = new JCheckBox("Disponible para jugar");
        chkDisponible.setSelected(campeon.isDisponible());
        chkDisponible.setEnabled(false);
        chkDisponible.setOpaque(false);
        panelDisponible.add(chkDisponible);

        // Habilidades
        JPanel panelHabilidades = new JPanel(new BorderLayout(5, 0));
        panelHabilidades.setOpaque(false);
        JLabel lblHabilidades = new JLabel("Habilidades: ");
        JLabel lblNumHabilidades = new JLabel(campeon.getHabilidades().size() + " habilidades");
        panelHabilidades.add(lblHabilidades, BorderLayout.WEST);
        panelHabilidades.add(lblNumHabilidades, BorderLayout.CENTER);

        // Añadir todas las estadísticas
        panelEstadisticas.add(panelDificultad);
        panelEstadisticas.add(panelPoder);
        panelEstadisticas.add(panelDisponible);
        panelEstadisticas.add(panelHabilidades);

        // Añadir imagen y estadísticas al panel central
        panelCentral.add(panelImagen);
        panelCentral.add(panelEstadisticas);

        // Panel inferior con información adicional
        JTextArea txtDescripcion = new JTextArea();
        txtDescripcion.setText("El campeón " + campeon.getNombre() +
                " es un personaje de rol " + campeon.getRol() +
                ". Su nivel de poder es " + campeon.getPoder() +
                " y tiene una dificultad de " + campeon.getDificultad() + "/10.\n\n" +
                "Fue lanzado el " + campeon.getFechaLanzamiento() + ".");
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEditable(false);
        txtDescripcion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDescripcion.setBackground(new Color(245, 245, 245));

        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setBorder(BorderFactory.createTitledBorder("Descripción"));

        // Añadir todos los componentes a la tarjeta
        panelCampeon.add(panelInfo, BorderLayout.NORTH);
        panelCampeon.add(panelCentral, BorderLayout.CENTER);
        panelCampeon.add(scrollDescripcion, BorderLayout.SOUTH);

        // Añadir la tarjeta al panel de tarjetas
        panelTarjetas.add(panelCampeon, campeon.getNombre());
    }

    /**
     * Determina un color basado en el rol del campeón.
     * Cada rol tiene asignado un color distintivo.
     * 
     * @param rol El rol del campeón
     * @return El color asignado al rol
     */
    private Color getColorPorRol(String rol) {
        switch (rol.toLowerCase()) {
            case "mid":
                return new Color(153, 51, 255); // Morado
            case "adc":
                return new Color(255, 102, 0); // Naranja
            case "top":
                return new Color(204, 51, 0); // Rojo oscuro
            case "jungler":
                return new Color(0, 153, 0); // Verde
            case "support":
                return new Color(51, 204, 255); // Azul claro
            default:
                return new Color(102, 102, 102); // Gris
        }
    }

    /**
     * Muestra la tarjeta del campeón seleccionado en el combo box.
     * Utiliza el CardLayout para cambiar entre las tarjetas.
     */
    private void mostrarCampeonSeleccionado() {
        String nombreCampeon = (String) cmbCampeones.getSelectedItem();
        if (nombreCampeon != null) {
            layoutTarjetas.show(panelTarjetas, nombreCampeon);
        }
    }
}