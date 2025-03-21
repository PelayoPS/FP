package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import logica.Liga;
import modelo.Equipo;
import modelo.Jugador;

/**
 * Ventana para mostrar y gestionar los equipos de League of Legends.
 * Esta clase proporciona una interfaz gráfica que muestra un listado de equipos
 * y permite visualizar los detalles de cada uno, incluyendo sus jugadores.
 * 
 * @author PelayoPS
 * @version 1.0
 */
public class EquiposFrame extends JFrame {

    /** Instancia de Liga que contiene los datos de los equipos */
    private Liga liga;

    /** Lista de equipos a mostrar en la interfaz */
    private List<Equipo> equipos;

    // Componentes de la interfaz
    /** Tabla para mostrar el listado de equipos */
    private JTable tablaEquipos;

    /** Modelo de datos para la tabla de equipos */
    private DefaultTableModel modeloTabla;

    /** Lista para mostrar los jugadores de un equipo seleccionado */
    private JList<String> listaJugadores;

    /** Modelo de datos para la lista de jugadores */
    private DefaultListModel<String> modeloLista;

    /** Panel para mostrar los detalles del equipo seleccionado */
    private JPanel panelDetalle;

    /**
     * Constructor de la clase EquiposFrame.
     * Inicializa la ventana y configura los componentes para mostrar
     * la información de los equipos y sus jugadores.
     *
     * @param liga Liga que contiene los equipos a visualizar
     */
    public EquiposFrame(Liga liga) {
        this.liga = liga;
        this.equipos = liga.getEquipos();

        // Configurar la ventana
        setTitle("Gestión de Equipos");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear y configurar los componentes
        inicializarComponentes();
        cargarDatos();
    }

    /**
     * Inicializa y configura todos los componentes de la interfaz gráfica.
     * Crea los paneles, tabla, lista y otros elementos necesarios para
     * mostrar la información de los equipos.
     */
    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel superior con título
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitulo = new JLabel("EQUIPOS DE LEAGUE OF LEGENDS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(51, 153, 255));
        panelTitulo.add(lblTitulo);

        // Panel izquierdo con tabla de equipos
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Listado de Equipos"));

        // Crear modelo de tabla y tabla
        String[] columnas = { "Nombre", "Jugadores" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEquipos = new JTable(modeloTabla);
        tablaEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEquipos.getTableHeader().setReorderingAllowed(false);

        // Configurar el renderizado de las celdas
        tablaEquipos.setRowHeight(25);
        tablaEquipos.getColumnModel().getColumn(0).setPreferredWidth(150);
        tablaEquipos.getColumnModel().getColumn(1).setPreferredWidth(50);

        // Añadir listener de selección
        tablaEquipos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaEquipos.getSelectedRow() != -1) {
                mostrarDetalleEquipo(tablaEquipos.getSelectedRow());
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tablaEquipos);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // Panel derecho con detalle del equipo
        panelDetalle = new JPanel(new BorderLayout(10, 10));
        panelDetalle.setBorder(BorderFactory.createTitledBorder("Detalle del Equipo"));

        // Crear lista de jugadores
        modeloLista = new DefaultListModel<>();
        listaJugadores = new JList<>(modeloLista);
        listaJugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaJugadores.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollLista = new JScrollPane(listaJugadores);
        JLabel lblJugadores = new JLabel("Jugadores del equipo:");
        lblJugadores.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel panelListaJugadores = new JPanel(new BorderLayout(5, 5));
        panelListaJugadores.add(lblJugadores, BorderLayout.NORTH);
        panelListaJugadores.add(scrollLista, BorderLayout.CENTER);

        // Panel de información adicional
        JPanel panelInfo = new JPanel(new GridLayout(3, 1, 5, 5));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblInfo1 = new JLabel("Seleccione un equipo para ver sus detalles");
        JLabel lblInfo2 = new JLabel("• La información se carga desde el XML");
        JLabel lblInfo3 = new JLabel("• Los jugadores se muestran en la lista");
        panelInfo.add(lblInfo1);
        panelInfo.add(lblInfo2);
        panelInfo.add(lblInfo3);

        // Añadir componentes al panel de detalle
        panelDetalle.add(panelListaJugadores, BorderLayout.CENTER);
        panelDetalle.add(panelInfo, BorderLayout.SOUTH);

        // Panel divisor para tabla y detalle
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelTabla, panelDetalle);
        splitPane.setDividerLocation(400);
        splitPane.setResizeWeight(0.5);

        // Añadir componentes al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(splitPane, BorderLayout.CENTER);

        // Establecer el panel principal
        setContentPane(panelPrincipal);
    }

    /**
     * Carga los datos de los equipos en la tabla y limpia el panel de detalles.
     * Este método se llama después de inicializar los componentes para
     * mostrar la información inicial.
     */
    private void cargarDatos() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Cargar equipos en la tabla
        for (Equipo equipo : equipos) {
            Object[] fila = {
                    equipo.getNombre(),
                    equipo.getJugadores().size()
            };
            modeloTabla.addRow(fila);
        }

        // Limpiar detalle
        modeloLista.clear();
    }

    /**
     * Muestra la información detallada del equipo seleccionado.
     * Actualiza la lista de jugadores y el título del panel de detalles.
     * 
     * @param indice Índice del equipo seleccionado en la tabla
     */
    private void mostrarDetalleEquipo(int indice) {
        if (indice >= 0 && indice < equipos.size()) {
            Equipo equipo = equipos.get(indice);

            // Actualizar título del panel
            ((javax.swing.border.TitledBorder) panelDetalle.getBorder()).setTitle("Equipo: " + equipo.getNombre());
            panelDetalle.repaint();

            // Limpiar y cargar lista de jugadores
            modeloLista.clear();
            for (Jugador jugador : equipo.getJugadores()) {
                modeloLista.addElement(jugador.getNombre() + " (" + jugador.getRol() + ")");
            }
        }
    }
}