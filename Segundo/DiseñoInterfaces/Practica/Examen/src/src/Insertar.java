package src;

import javax.swing.*;
import java.awt.*;
import utils.Database;
import javax.swing.border.EmptyBorder;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Insertar {
	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private JTextField txtProfesion;
	private JTextField txtTelefono;
	private JTable tablaResultados; // Cambiado de JList a JTable
	private DefaultTableModel modeloTabla; // Cambiado de DefaultListModel a DefaultTableModel
	private Database db;

	/**
	 * Create the application.
	 */
	public Insertar() {
		initialize();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		db = new Database();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Registro de personas");
		frame.setBounds(100, 100, 700, 708);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.getContentPane().setBackground(new Color(240, 240, 245));

		// Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(15, 15));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainPanel.setBackground(new Color(240, 240, 245));
		frame.getContentPane().add(mainPanel);

		// Título
		JLabel titleLabel = new JLabel("Registro de Personal", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(new Color(50, 50, 50));
		titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		// Panel de formulario
		JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		formPanel.setBackground(new Color(240, 240, 245));

		// Estilo común para las etiquetas
		Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
		Color labelColor = new Color(70, 70, 70);

		JLabel[] labels = {
				new JLabel("Nombre:"),
				new JLabel("Edad:"),
				new JLabel("Profesión:"),
				new JLabel("Teléfono:")
		};

		for (JLabel label : labels) {
			label.setFont(labelFont);
			label.setForeground(labelColor);
		}

		formPanel.add(labels[0]);
		txtNombre = createStyledTextField();
		formPanel.add(txtNombre);

		formPanel.add(labels[1]);
		txtEdad = createStyledTextField();
		formPanel.add(txtEdad);

		formPanel.add(labels[2]);
		txtProfesion = createStyledTextField();
		formPanel.add(txtProfesion);

		formPanel.add(labels[3]);
		txtTelefono = createStyledTextField();
		formPanel.add(txtTelefono);

		// Panel central para el formulario
		JPanel centerPanel = new JPanel(new BorderLayout(0, 15));
		centerPanel.setBackground(new Color(240, 240, 245));
		centerPanel.add(formPanel, BorderLayout.CENTER);

		// Botón insertar
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnInsertar.setBackground(new Color(70, 130, 180));
		btnInsertar.setForeground(Color.WHITE);
		btnInsertar.setFocusPainted(false);
		btnInsertar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnInsertar.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(new Color(240, 240, 245));
		buttonPanel.add(btnInsertar);
		centerPanel.add(buttonPanel, BorderLayout.SOUTH);

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// Panel de resultados
		JPanel resultsPanel = new JPanel(new BorderLayout(0, 10));
		resultsPanel.setBackground(new Color(240, 240, 245));
		resultsPanel.setPreferredSize(new Dimension(650, 200)); // Añadir tamaño preferido

		JLabel resultsTitle = new JLabel("Personas con la misma profesión", SwingConstants.CENTER);
		resultsTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
		resultsTitle.setForeground(new Color(70, 70, 70));
		resultsPanel.add(resultsTitle, BorderLayout.NORTH);

		// Crear el modelo de tabla con columnas
		String[] columnNames = { "Nombre", "Edad", "Profesión", "Teléfono" };
		modeloTabla = new DefaultTableModel(columnNames, 0);
		tablaResultados = new JTable(modeloTabla);
		tablaResultados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tablaResultados.setRowHeight(30);
		tablaResultados.setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane(tablaResultados);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
		scrollPane.setPreferredSize(new Dimension(630, 150)); // Añadir tamaño preferido
		resultsPanel.add(scrollPane, BorderLayout.CENTER);

		mainPanel.add(resultsPanel, BorderLayout.SOUTH);

		btnInsertar.addActionListener(e -> {
			try {
				String nombre = txtNombre.getText();
				int edad = Integer.parseInt(txtEdad.getText());
				String profesion = txtProfesion.getText();
				String telefono = txtTelefono.getText();

				if (db.insertarEmpleado(nombre, edad, profesion, telefono)) {
					JOptionPane.showMessageDialog(frame, "Empleado insertado correctamente");

					// Buscar empleados con la misma profesión
					List<String[]> empleadosMismaProfesion = db.buscarPorProfesion(profesion);
					modeloTabla.setRowCount(0); // Limpiar tabla
					for (String[] empleado : empleadosMismaProfesion) {
						modeloTabla.addRow(new Object[] {
								empleado[1], // nombre
								empleado[2], // edad
								empleado[3], // profesion
								empleado[4] // telefono
						});
					}

					// Limpiar campos
					txtNombre.setText("");
					txtEdad.setText("");
					txtProfesion.setText("");
					txtTelefono.setText("");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(frame, "La edad debe ser un número válido",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private JTextField createStyledTextField() {
		JTextField textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(200, 200, 200)),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		return textField;
	}
}
