package src;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JColorChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la ventana principal de la calculadora.
 */
public class Window extends JFrame {

    private static final long serialVersionUID = 417915897185629027L;
    private JPanel mainPanel;
    private DefaultListModel<String> resultListModel;
    private JList<String> resultList;

    /**
     * Constructor de la clase Window.
     * 
     * @param title Título de la ventana.
     */
    public Window(String title) {
        // crea la ventana principal
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(350, 450));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        // asigna un icono a la ventana
        setIconImage(new ImageIcon("icon.png").getImage());
        // crea y asigna la barra de menú
        setJMenuBar(createMenuBar());
        // crea y asigna el panel principal
        mainPanel = new JPanel();
        // layout del panel principal
        mainPanel.setLayout(new GridLayout(3, 1, 20, 10));
        mainPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(10, 10, 10, 10),
                        BorderFactory.createEmptyBorder()));

        // crea y asigna el modelo de la lista de resultados
        resultListModel = new DefaultListModel<>();
        resultList = new JList<>(resultListModel);

        // agrega los paneles al panel principal
        mainPanel.add(createData());
        mainPanel.add(createOperations());
        mainPanel.add(createResult());

        // agrega el panel principal a la ventana
        add(mainPanel, BorderLayout.CENTER);

    }

    /**
     * Crea y devuelve el panel principal.
     * 
     * @return El panel principal.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Crea y devuelve el panel de ingreso de datos.
     * 
     * @return El panel de ingreso de datos.
     */
    public JPanel createData() {
        // crea el panel de ingreso de datos
        JPanel dataPanel = new JPanel(new GridLayout(2, 2, 0, 10));
        dataPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK),
                        "Ingreso de datos",
                        TitledBorder.LEFT,
                        TitledBorder.DEFAULT_POSITION));
        // crea un margen interno al panel
        dataPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        dataPanel.getBorder(),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // crea los componentes del panel
        JLabel labelDato1 = new JLabel("Dato1");
        JTextField textFieldDato1 = new JTextField(10);
        JLabel labelDato2 = new JLabel("Dato2");
        JTextField textFieldDato2 = new JTextField(10);

        // agrega los componentes al panel
        dataPanel.add(labelDato1);
        dataPanel.add(textFieldDato1);
        dataPanel.add(labelDato2);
        dataPanel.add(textFieldDato2);

        return dataPanel;
    }

    /**
     * Crea y devuelve el panel de operaciones.
     * 
     * @return El panel de operaciones.
     */
    private JPanel createOperations() {
        // crea el panel de operaciones
        JPanel operationsPanel = new JPanel();
        // crea una lista de operaciones
        List<String> operaciones = new ArrayList<String>();
        operaciones.add("+");
        operaciones.add("-");
        operaciones.add("x");
        operaciones.add("/");
        // layout del panel de operaciones
        operationsPanel.setLayout(new GridLayout(1, 4, 10, 10));
        operationsPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK),
                        "Operaciones",
                        TitledBorder.LEFT,
                        TitledBorder.DEFAULT_POSITION));
        // crea un margen interno al panel
        operationsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        operationsPanel.getBorder(),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        // crea un botón para cada operación
        for (String operacion : operaciones) {
            JButton button = new JButton(operacion);
            button.setFont(new Font("Arial", Font.PLAIN, 25));
            setOperation(button);
            operationsPanel.add(button);
        }

        return operationsPanel;
    }

    /**
     * Asigna la operación correspondiente al botón.
     * 
     * @param button El botón al que se le asignará la operación.
     */
    private void setOperation(JButton button) {
        // agrega un listener al botón
        button.addActionListener(e -> {
            JPanel dataPanel = (JPanel) mainPanel.getComponent(0);
            JTextField textFieldDato1 = (JTextField) dataPanel.getComponent(1);
            JTextField textFieldDato2 = (JTextField) dataPanel.getComponent(3);
            double dato1 = Double.parseDouble(textFieldDato1.getText());
            double dato2 = Double.parseDouble(textFieldDato2.getText());
            double result = 0;
            // consigue el texto para el último resultado
            JLabel label = (JLabel) ((JPanel) mainPanel.getComponent(2)).getComponent(0);


            // realiza la operación correspondiente
            switch (button.getText()) {
                case "+":
                    result = Calculator.add(dato1, dato2);
                    // agrega el resultado a la lista
                    resultListModel.addElement(dato1 + " " + button.getText() + " " + dato2 + " = " + result);
                    break;
                case "-":
                    result = Calculator.subtract(dato1, dato2);

                    break;
                case "x":
                    result = Calculator.multiply(dato1, dato2);
                    break;
                case "/":
                    result = Calculator.divide(dato1, dato2);
                    break;
                default:
                    break;
            }
            // asigna el texto para el último resultado
            label.setText("Último resultado: " + dato1 + " " + button.getText() + " " + dato2 + " = " + result);
            resultList.setModel(resultListModel);
        });
    }

    /**
     * Crea y devuelve el panel de resultados.
     * 
     * @return El panel de resultados.
     */
    private JPanel createResult() {
        // crea el panel de resultados
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK),
                        "Resultado",
                        TitledBorder.LEFT,
                        TitledBorder.DEFAULT_POSITION));
        // crea un margen interno al panel
        resultPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        resultPanel.getBorder(),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        // crea un texto para mostrar el último resultado
        JLabel label = new JLabel("Último resultado:");
        resultPanel.add(label, BorderLayout.NORTH);
        // crea un scroll pane para la lista de resultados
        JScrollPane scrollPane = new JScrollPane(resultList);
        resultPanel.add(scrollPane, BorderLayout.CENTER);
        return resultPanel;
    }

    /**
     * Crea y devuelve la barra de menú.
     * 
     * @return La barra de menú.
     */
    private JMenuBar createMenuBar() {
        // crea la barra del menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");

        // asigna un listener al item de menú "Salir"
        JMenuItem exitMenuItem = new JMenuItem("Salir");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres salir?",
                        "Confirmar salida", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // asigna un listener al item de menú "Color"
        JMenuItem colorMenuItem = new JMenuItem("Color");
        colorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color textColor = JColorChooser.showDialog(null, "Elige el color del texto", Color.BLACK);
                Color backgroundColor = JColorChooser.showDialog(null, "Elige el color de fondo", Color.WHITE);
                if (textColor != null && backgroundColor != null) {
                    resultList.setForeground(textColor);
                    resultList.setBackground(backgroundColor);
                }
            }
        });

        // agrega los componentes al menú
        menu.add(exitMenuItem);
        menu.add(colorMenuItem);
        menuBar.add(menu);

        return menuBar;
    }

}
