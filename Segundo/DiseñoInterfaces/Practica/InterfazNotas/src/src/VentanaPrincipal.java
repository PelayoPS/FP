package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Clase VentanaPrincipal que representa la interfaz gráfica de la aplicación de notas.
 */
public class VentanaPrincipal extends JFrame {
    private JLabel[] lblNotas;
    private JTextField[] textFields;
    private JComboBox<String> cOperacion;
    private JButton btnCalcular, btnLimpiar;
    private JLabel lblOperacion, lblPromedio, lblDesviacion, lblNotaMayor, lblNotaMenor, lblResultado;
    private static final String[] OPERACIONES= {"","+","-","*","/"};

    /**
     * Constructor de la clase VentanaPrincipal.
     * 
     * @param titulo El título de la ventana.
     */
    public VentanaPrincipal(String titulo) {
        super(titulo);
        setLayout(null);
        setBounds(100, 100, 400, 800);

        // Inicializar componentes
        lblOperacion = new JLabel("Operación:");
        lblOperacion.setFont(new Font("", Font.BOLD, 20));
        lblOperacion.setBounds(25, 25, 150, 50);
        add(lblOperacion);

        lblNotas = new JLabel[5];
        textFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            lblNotas[i] = new JLabel("Nota " + (i + 1) + ": ");
            lblNotas[i].setFont(new Font("", Font.BOLD, 20));
            lblNotas[i].setBounds(25, 75 + i * 50, 150, 50);
            add(lblNotas[i]);

            textFields[i] = new JTextField(2);
            textFields[i].setFont(new Font("", Font.BOLD, 20));
            textFields[i].setBounds(175, 85 + i * 50, 150, 30);
            // Valor por defecto -1 para evitar excepciones de puntero nulo
            textFields[i].setText("-1");
            add(textFields[i]);
        }

        cOperacion = new JComboBox<>(OPERACIONES);
        cOperacion.setFont(new Font("", Font.BOLD, 20));
        cOperacion.setBounds(175, 35, 150, 30);
        add(cOperacion);

        btnCalcular = new JButton("Calcular");
        btnCalcular.setFont(new Font("", Font.BOLD, 20));
        btnCalcular.setBounds(25, 350, 150, 40);
        add(btnCalcular);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("", Font.BOLD, 20));
        btnLimpiar.setBounds(200, 350, 150, 40);
        add(btnLimpiar);

        lblPromedio = new JLabel("Promedio = ");
        lblPromedio.setFont(new Font("", Font.BOLD, 20));
        lblPromedio.setBounds(25, 425, 250, 50);
        add(lblPromedio);

        lblDesviacion = new JLabel("Desviación = ");
        lblDesviacion.setFont(new Font("", Font.BOLD, 20));
        lblDesviacion.setBounds(25, 475, 250, 50);
        add(lblDesviacion);

        lblNotaMayor = new JLabel("Nota mayor = ");
        lblNotaMayor.setFont(new Font("", Font.BOLD, 20));
        lblNotaMayor.setBounds(25, 525, 250, 50);
        add(lblNotaMayor);

        lblNotaMenor = new JLabel("Nota menor = ");
        lblNotaMenor.setFont(new Font("", Font.BOLD, 20));
        lblNotaMenor.setBounds(25, 575, 250, 50);
        add(lblNotaMenor);

        lblResultado = new JLabel("Resultado = ");
        lblResultado.setFont(new Font("", Font.BOLD, 20));
        lblResultado.setBounds(25, 625, 250, 50);
        add(lblResultado);

        // Añadir funcionalidad a los botones
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Notas notas = new Notas();
                for (JTextField textField : textFields) {
                    notas.obtenerNotas().add(textField.getText());
                }
                double promedio = notas.calcularPromedio();
                double desviacion = notas.calcularDesviacion();
                double notaMayor = notas.calcularMayor();
                double notaMenor = notas.calcularMenor();
                String resultado = "";
                switch (cOperacion.getSelectedIndex()) {
                    //String[] operacion= {"","+","-","*","/"};
                    case 0:
                        resultado = "Resultado = ";
                        break;
                    case 1:
                        resultado = "Resultado = " + notas.opSuma();
                        break;
                    case 2:
                        resultado = "Resultado = " + notas.opResta();
                        break;
                    case 3:
                        resultado = "Resultado = " + notas.opProducto();
                        break;
                    case 4:
                        resultado = "Resultado = " + notas.opDivision();
                        break;                    
                }
                lblPromedio.setText("Promedio = " + promedio);
                lblDesviacion.setText("Desviación = " + desviacion);
                lblNotaMayor.setText("Nota mayor = " + notaMayor);
                lblNotaMenor.setText("Nota menor = " + notaMenor);
                lblResultado.setText(resultado);
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JTextField textField : textFields) {
                    textField.setText("-1");
                }
                lblResultado.setText("Resultado = ");
            }
        });
    }
}
