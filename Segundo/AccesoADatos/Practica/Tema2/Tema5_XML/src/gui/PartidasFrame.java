package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;
import logica.Liga;
import modelo.Partida;

/**
 * Ventana para visualizar las partidas de League of Legends.
 * Esta clase proporciona una interfaz gráfica que muestra un historial de
 * partidas
 * y permite ver los detalles de cada partida seleccionada, incluyendo
 * estadísticas
 * y resultados.
 * 
 * @author PelayoPS
 * @version 1.0
 */
public class PartidasFrame extends JFrame {

    /** La liga que contiene las partidas */
    private Liga liga;

    /** Lista de partidas a mostrar */
    private List<Partida> partidas;

    // Componentes de la interfaz
    /** Tabla que muestra el listado de partidas */
    private JTable tablaPartidas;
    /** Modelo de datos para la tabla de partidas */
    private DefaultTableModel modeloTabla;
    /** Panel que muestra el detalle de la partida seleccionada */
    private JPanel panelDetalle;
    /** Etiqueta que muestra el nombre del equipo */
    private JLabel lblEquipoValor;
    /** Etiqueta que muestra el nombre del oponente */
    private JLabel lblOponenteValor;
    /** Etiqueta que muestra el resultado de la partida */
    private JLabel lblResultadoValor;
    /** Etiqueta que muestra la fecha de la partida */
    private JLabel lblFechaValor;
    /** Etiqueta que muestra la duración de la partida */
    private JLabel lblDuracionValor;
    /** Panel que contiene las estadísticas de la partida */
    private JPanel panelEstadisticas;

    /**
     * Constructor de la ventana de partidas.
     * Inicializa la ventana y configura todos sus componentes para mostrar
     * el historial de partidas de una liga.
     *
     * @param liga La liga que contiene las partidas a mostrar
     */
    public PartidasFrame(Liga liga) {
        this.liga = liga;
        this.partidas = liga.getPartidas();

        // Configurar la ventana
        setTitle("Historial de Partidas");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear y configurar los componentes
        inicializarComponentes();
        cargarDatos();
    }

    /**
     * Inicializa y configura todos los componentes de la interfaz gráfica.
     * Crea la tabla de partidas, el panel de detalles y las estadísticas.
     */
    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel superior con título
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitulo = new JLabel("HISTORIAL DE PARTIDAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(255, 102, 102));
        panelTitulo.add(lblTitulo);

        // Panel izquierdo con tabla de partidas
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Partidas Registradas"));

        // Crear modelo de tabla y tabla
        String[] columnas = { "Equipo", "Oponente", "Resultado" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPartidas = new JTable(modeloTabla);
        tablaPartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPartidas.getTableHeader().setReorderingAllowed(false);

        // Configurar el renderizado de las celdas
        tablaPartidas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Colorear según el resultado
                if (column == 2) {
                    String resultado = (String) value;
                    if (resultado.equals("Ganado")) {
                        c.setForeground(new Color(0, 153, 0)); // Verde
                    } else if (resultado.equals("Perdido")) {
                        c.setForeground(new Color(204, 0, 0)); // Rojo
                    } else {
                        c.setForeground(new Color(153, 102, 0)); // Amarillo oscuro
                    }
                } else {
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        });

        tablaPartidas.setRowHeight(25);

        // Añadir listener de selección
        tablaPartidas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaPartidas.getSelectedRow() != -1) {
                mostrarDetallePartida(tablaPartidas.getSelectedRow());
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tablaPartidas);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // Panel derecho con detalle de partida
        panelDetalle = new JPanel(new BorderLayout(10, 10));
        panelDetalle.setBorder(BorderFactory.createTitledBorder("Detalle de la Partida"));

        // Panel para información básica
        JPanel panelInfo = new JPanel(new GridLayout(5, 2, 10, 10));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblEquipo = new JLabel("Equipo:", JLabel.RIGHT);
        lblEquipo.setFont(new Font("Arial", Font.BOLD, 14));
        lblEquipoValor = new JLabel("-");
        lblEquipoValor.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblOponente = new JLabel("Oponente:", JLabel.RIGHT);
        lblOponente.setFont(new Font("Arial", Font.BOLD, 14));
        lblOponenteValor = new JLabel("-");
        lblOponenteValor.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblResultado = new JLabel("Resultado:", JLabel.RIGHT);
        lblResultado.setFont(new Font("Arial", Font.BOLD, 14));
        lblResultadoValor = new JLabel("-");
        lblResultadoValor.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblFecha = new JLabel("Fecha:", JLabel.RIGHT);
        lblFecha.setFont(new Font("Arial", Font.BOLD, 14));
        lblFechaValor = new JLabel("-");
        lblFechaValor.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblDuracion = new JLabel("Duración:", JLabel.RIGHT);
        lblDuracion.setFont(new Font("Arial", Font.BOLD, 14));
        lblDuracionValor = new JLabel("-");
        lblDuracionValor.setFont(new Font("Arial", Font.PLAIN, 14));

        panelInfo.add(lblEquipo);
        panelInfo.add(lblEquipoValor);
        panelInfo.add(lblOponente);
        panelInfo.add(lblOponenteValor);
        panelInfo.add(lblResultado);
        panelInfo.add(lblResultadoValor);
        panelInfo.add(lblFecha);
        panelInfo.add(lblFechaValor);
        panelInfo.add(lblDuracion);
        panelInfo.add(lblDuracionValor);

        // Panel para estadísticas
        panelEstadisticas = new JPanel();
        panelEstadisticas.setLayout(new BoxLayout(panelEstadisticas, BoxLayout.Y_AXIS));
        panelEstadisticas.setBorder(BorderFactory.createTitledBorder("Estadísticas"));

        // Panel de puntuación
        JPanel panelPuntuacion = new JPanel(new BorderLayout());
        panelPuntuacion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel puntuacionVisual = new JPanel(new GridLayout(1, 5, 5, 0));
        puntuacionVisual.add(new JLabel("Equipo", JLabel.CENTER));
        JProgressBar barraEquipo = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        barraEquipo.setValue(0);
        barraEquipo.setStringPainted(true);
        barraEquipo.setString("0");
        barraEquipo.setForeground(new Color(51, 153, 255));
        puntuacionVisual.add(barraEquipo);

        puntuacionVisual.add(new JLabel("vs", JLabel.CENTER));

        JProgressBar barraOponente = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        barraOponente.setValue(0);
        barraOponente.setStringPainted(true);
        barraOponente.setString("0");
        barraOponente.setForeground(new Color(255, 102, 102));
        puntuacionVisual.add(barraOponente);
        puntuacionVisual.add(new JLabel("Oponente", JLabel.CENTER));

        panelPuntuacion.add(new JLabel("Puntuación:", JLabel.CENTER), BorderLayout.NORTH);
        panelPuntuacion.add(puntuacionVisual, BorderLayout.CENTER);

        panelEstadisticas.add(panelPuntuacion);

        // Añadir espaciadores
        panelEstadisticas.add(Box.createVerticalGlue());

        // Añadir componentes al panel de detalle
        panelDetalle.add(panelInfo, BorderLayout.NORTH);
        panelDetalle.add(panelEstadisticas, BorderLayout.CENTER);

        // Panel de mensaje
        JPanel panelMensaje = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblMensaje = new JLabel("Seleccione una partida para ver los detalles");
        lblMensaje.setFont(new Font("Arial", Font.ITALIC, 12));
        panelMensaje.add(lblMensaje);

        panelDetalle.add(panelMensaje, BorderLayout.SOUTH);

        // Panel divisor para tabla y detalle
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelTabla, panelDetalle);
        splitPane.setDividerLocation(350);
        splitPane.setResizeWeight(0.5);

        // Añadir componentes al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(splitPane, BorderLayout.CENTER);

        // Establecer el panel principal
        setContentPane(panelPrincipal);
    }

    /**
     * Carga los datos de las partidas en la tabla.
     * Lee la lista de partidas y las muestra en la tabla con el equipo,
     * oponente y resultado.
     */
    private void cargarDatos() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Cargar partidas en la tabla
        for (Partida partida : partidas) {
            Object[] fila = {
                    partida.getEquipo(),
                    partida.getOponente(),
                    partida.getResultado()
            };
            modeloTabla.addRow(fila);
        }
    }

    /**
     * Muestra los detalles de la partida seleccionada.
     * Actualiza el panel de detalles con la información de la partida,
     * incluyendo estadísticas y puntuaciones.
     *
     * @param indice El índice de la partida seleccionada en la lista
     */
    private void mostrarDetallePartida(int indice) {
        if (indice >= 0 && indice < partidas.size()) {
            Partida partida = partidas.get(indice);

            // Actualizar título del panel
            ((javax.swing.border.TitledBorder) panelDetalle.getBorder())
                    .setTitle("Partida: " + partida.getEquipo() + " vs " + partida.getOponente());
            panelDetalle.repaint();

            // Actualizar información básica
            lblEquipoValor.setText(partida.getEquipo());
            lblOponenteValor.setText(partida.getOponente());

            // Colorear el resultado
            String resultado = partida.getResultado();
            lblResultadoValor.setText(resultado);
            if (resultado.equals("Ganado")) {
                lblResultadoValor.setForeground(new Color(0, 153, 0)); // Verde
            } else if (resultado.equals("Perdido")) {
                lblResultadoValor.setForeground(new Color(204, 0, 0)); // Rojo
            } else {
                lblResultadoValor.setForeground(new Color(153, 102, 0)); // Amarillo oscuro
            }

            lblFechaValor.setText(partida.getFecha() != null ? partida.getFecha().toString() : "No disponible");
            lblDuracionValor.setText(partida.getDuracionMinutos() + " minutos");

            // Actualizar estadísticas
            JPanel puntuacionPanel = (JPanel) ((JPanel) panelEstadisticas.getComponent(0)).getComponent(1);
            JProgressBar barraEquipo = (JProgressBar) puntuacionPanel.getComponent(1);
            JProgressBar barraOponente = (JProgressBar) puntuacionPanel.getComponent(3);

            // Establecer valores de puntuación (simulado o real)
            int puntuacionEquipo = partida.getPuntuacionEquipo();
            int puntuacionOponente = partida.getPuntuacionOponente();

            // Si no hay puntuación, simular valores según el resultado
            if (puntuacionEquipo == 0 && puntuacionOponente == 0) {
                if (resultado.equals("Ganado")) {
                    puntuacionEquipo = 75 + (int) (Math.random() * 26); // 75-100
                    puntuacionOponente = 30 + (int) (Math.random() * 41); // 30-70
                } else if (resultado.equals("Perdido")) {
                    puntuacionEquipo = 30 + (int) (Math.random() * 41); // 30-70
                    puntuacionOponente = 75 + (int) (Math.random() * 26); // 75-100
                } else { // Empate
                    puntuacionEquipo = 50 + (int) (Math.random() * 31); // 50-80
                    puntuacionOponente = 50 + (int) (Math.random() * 31); // 50-80
                }
            }

            barraEquipo.setValue(puntuacionEquipo);
            barraEquipo.setString(Integer.toString(puntuacionEquipo));

            barraOponente.setValue(puntuacionOponente);
            barraOponente.setString(Integer.toString(puntuacionOponente));

            // Añadir o actualizar estadísticas detalladas
            // Eliminar estadísticas anteriores si hay más de la puntuación
            while (panelEstadisticas.getComponentCount() > 2) {
                panelEstadisticas.remove(1);
            }

            // Crear estadísticas detalladas (usando datos reales o simulados)
            JPanel panelEstDetalladas = new JPanel(new GridLayout(1, 2, 10, 0));
            panelEstDetalladas.setBorder(BorderFactory.createTitledBorder("Detalles de la partida"));

            // Estadísticas del equipo
            JPanel estEquipo = new JPanel(new GridLayout(5, 1, 5, 5));
            estEquipo.setBorder(BorderFactory.createTitledBorder(partida.getEquipo()));
            estEquipo.add(new JLabel("Kills: " + simularEstadistica(15, 5)));
            estEquipo.add(new JLabel("Deaths: " + simularEstadistica(10, 5)));
            estEquipo.add(new JLabel("Assists: " + simularEstadistica(20, 8)));
            estEquipo.add(new JLabel("Oro: " + simularEstadistica(15000, 5000)));
            estEquipo.add(new JLabel("Torres: " + simularEstadistica(7, 3)));

            // Estadísticas del oponente
            JPanel estOponente = new JPanel(new GridLayout(5, 1, 5, 5));
            estOponente.setBorder(BorderFactory.createTitledBorder(partida.getOponente()));
            estOponente.add(new JLabel("Kills: " + simularEstadistica(15, 5)));
            estOponente.add(new JLabel("Deaths: " + simularEstadistica(10, 5)));
            estOponente.add(new JLabel("Assists: " + simularEstadistica(20, 8)));
            estOponente.add(new JLabel("Oro: " + simularEstadistica(15000, 5000)));
            estOponente.add(new JLabel("Torres: " + simularEstadistica(7, 3)));

            panelEstDetalladas.add(estEquipo);
            panelEstDetalladas.add(estOponente);

            panelEstadisticas.add(panelEstDetalladas, 1);

            // Refrescar el panel
            panelEstadisticas.revalidate();
            panelEstadisticas.repaint();
        }
    }

    /**
     * Genera un valor aleatorio para simular estadísticas de juego.
     *
     * @param base      El valor base para la estadística
     * @param variacion La variación máxima permitida sobre el valor base
     * @return Un valor aleatorio dentro del rango [base-variacion, base+variacion]
     */
    private int simularEstadistica(int base, int variacion) {
        return base - variacion + (int) (Math.random() * (variacion * 2 + 1));
    }
}