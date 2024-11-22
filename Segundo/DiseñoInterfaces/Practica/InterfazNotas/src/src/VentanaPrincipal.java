package src;

import java.awt.*;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    private double[] notas;
    private JTextField[] textFieldsNotas;
    private JLabel labelPromedio;
    private JLabel labelDesviacion;
    private JLabel labelMenor;
    private JLabel labelMayor;
    private JLabel labelResultadoOperacion;
    private JComboBox<String> comboBoxOperacion; // ComboBox para seleccionar la operación

    public VentanaPrincipal() {
        super("Notas");
        this.setLayout(new BorderLayout());
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel para las notas
        JPanel panelNotas = new JPanel();
        panelNotas.setLayout(new GridLayout(5, 2));
        this.add(panelNotas, BorderLayout.NORTH);

        // Panel para la operación
        JPanel panelOperacion = new JPanel();
        panelOperacion.setLayout(new FlowLayout());
        this.add(panelOperacion, BorderLayout.CENTER);

        // Panel para el resultado
        JPanel panelResultado = new JPanel();
        panelResultado.setLayout(new GridLayout(5, 1));
        this.add(panelResultado, BorderLayout.SOUTH);

        // Inicializar las variables
        notas = new double[5];
        textFieldsNotas = new JTextField[5];
        labelPromedio = new JLabel("Promedio: ");
        labelDesviacion = new JLabel("Desviación: ");
        labelMenor = new JLabel("Menor: ");
        labelMayor = new JLabel("Mayor: ");
        labelResultadoOperacion = new JLabel("Resultado: ");
        comboBoxOperacion = new JComboBox<String>();
        comboBoxOperacion.addItem("Promedio");
        comboBoxOperacion.addItem("Desviación");
        comboBoxOperacion.addItem("Menor");
        comboBoxOperacion.addItem("Mayor");

        // Agregar los componentes al panel de notas
        for (int i = 0; i < 5; i++) {
            textFieldsNotas[i] = new JTextField();
            panelNotas.add(new JLabel("Nota " + (i + 1) + ": "));
            panelNotas.add(textFieldsNotas[i]);
        }

        this.setVisible(true);
    }
}
