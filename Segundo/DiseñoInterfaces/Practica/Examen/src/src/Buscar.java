package src;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import utils.Database;
import java.util.List;

public class Buscar {

	private JFrame frame;
	private JTextField textFieldProfesion;
	private JList<String> listResultados;
	private DefaultListModel<String> listModel;
	private Database db;

	/**
	 * Create the application.
	 */
	public Buscar() {
		db = new Database();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("BÚSQUEDA POR PROFESIÓN");
		frame.setBounds(100, 100, 575, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Panel principal con margen
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		mainPanel.setBackground(new Color(240, 240, 245));

		// Panel superior para la búsqueda
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		searchPanel.setBackground(new Color(240, 240, 245));
		searchPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(200, 200, 200)),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		JLabel lblProfesion = new JLabel("PROFESIÓN:");
		lblProfesion.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblProfesion.setForeground(new Color(50, 50, 50));

		textFieldProfesion = new JTextField(20);
		textFieldProfesion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldProfesion.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(180, 180, 180)),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBuscar.setBackground(new Color(70, 130, 180));
		btnBuscar.setForeground(new Color(0, 0, 0));
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBorder(new EmptyBorder(8, 15, 8, 15));
		btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBuscar.addActionListener(e -> {
			buscarEmpleados();
		});

		searchPanel.add(lblProfesion);
		searchPanel.add(textFieldProfesion);
		searchPanel.add(btnBuscar);

		// Lista de resultados
		listModel = new DefaultListModel<>();
		listResultados = new JList<>(listModel);
		listResultados.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		listResultados.setBackground(Color.WHITE);
		listResultados.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		listResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listResultados.setFixedCellHeight(25);

		JScrollPane scrollPane = new JScrollPane(listResultados);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

		mainPanel.add(searchPanel, BorderLayout.NORTH);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		frame.setContentPane(mainPanel);
		frame.getRootPane().setDefaultButton(btnBuscar);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}

	private void buscarEmpleados() {
		listModel.clear();
		String profesion = textFieldProfesion.getText().trim();

		if (!profesion.isEmpty()) {
			List<String[]> resultados = db.buscarPorProfesion(profesion);

			if (resultados.isEmpty()) {
				listModel.addElement("No se encontraron resultados para: " + profesion);
			} else {
				for (String[] empleado : resultados) {
					listModel.addElement(String.format("ID: %s - %s - Edad: %s - %s - Tel: %s",
							empleado[0], empleado[1], empleado[2], empleado[3], empleado[4]));
				}
			}
		} else {
			listModel.addElement("Por favor, introduce una profesión para buscar");
		}
	}

	public JFrame getFrame() {
		return frame;
	}
}
