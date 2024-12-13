package Persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Logica.Cliente;
import Logica.Comercial;
import Logica.Pedido;

public class GestorJDBC {
	private Connection con;
	private Statement st;

	public GestorJDBC() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/ventas", "root", "root");
		System.out.println("Base conectada ");
	}

	public boolean crearTablaLogin() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS Login (id INT AUTO_INCREMENT PRIMARY KEY, Nombre VARCHAR(50), Pass VARCHAR(50))";
		st = con.createStatement();
		st.executeUpdate(sql);
		if (st != null) {
			return true;
		}
		return false;

	}

	public int insertarDatosLogin(String[] nombres, String[] claves) throws SQLException {
		String sql = "INSERT INTO Login (Nombre, Pass) VALUES (?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		int result = 0;

		for (int i = 0; i < nombres.length; i++) {
			ps.setString(1, nombres[i]);
			ps.setString(2, claves[i]);
			ps.executeUpdate();
			result = i;
		}

		return result;
	}

	public String validarUsuario(String nombre, String pass) throws SQLException {
		String result = "";
		// case user not registered
		String sqlNames = "SELECT * FROM Login WHERE Nombre = ?";
		PreparedStatement ps = con.prepareStatement(sqlNames);
		ps.setString(1, nombre);
		ResultSet rs = ps.executeQuery();
		// if empty result set
		if (!rs.next()) {
			result = "Usuario no registrado";
		} else {
			// case user registered but incorrect password
			if (rs.getString("Pass") != pass) {
				result = "Usuario clave no correcto";
			}
		}
		// case user registered and correct password
		result = "Usuario correcto";

		return result;
	}

	public List<Pedido> obtenerDatosPedidos() throws SQLException {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		String sql = "SELECT * FROM Pedido";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			int idPedido = rs.getInt("id");
			double cantidad = rs.getDouble("total");
			Date fecha = rs.getDate("fecha");
			int idCliente = rs.getInt("id_cliente");
			int idComercial = rs.getInt("id_comercial");
			Pedido p = new Pedido(idPedido, cantidad, fecha, idCliente, idComercial);
			pedidos.add(p);
		}
		return pedidos;
	}

	public List<Cliente> obtenerClientesConPedidos() throws SQLException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		String sql = "SELECT * FROM Cliente WHERE id IN (SELECT id_cliente FROM Pedido)";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			int idCliente = rs.getInt("id");
			String nombre = rs.getString("nombre");
			String apellido1 = rs.getString("apellido1");
			String apellido2 = rs.getString("apellido2");
			String ciudad = rs.getString("ciudad");
			int categoria = rs.getInt("categoria");
			Cliente c = new Cliente(idCliente, nombre, apellido1, apellido2, ciudad, categoria);
			clientes.add(c);
		}
		return clientes;
	}

	public Pedido obtenerPedidoMayorValor() throws SQLException {
		Pedido pedido = null;
		String sql = "SELECT * FROM Pedido WHERE total = (SELECT MAX(total) FROM Pedido)";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			int idPedido = rs.getInt("id");
			double cantidad = rs.getDouble("total");
			Date fecha = rs.getDate("fecha");
			int idCliente = rs.getInt("id_cliente");
			int idComercial = rs.getInt("id_comercial");
			pedido = new Pedido(idPedido, cantidad, fecha, idCliente, idComercial);
		}
		return pedido;
	}

	public List<Comercial> obtenerComercialesSinPedidos() throws SQLException {
		List<Comercial> comerciales = new ArrayList<Comercial>();
		String sql = "SELECT * FROM Comercial WHERE id NOT IN (SELECT id_comercial FROM Pedido)";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			String nombre = rs.getString("nombre");
			String apellido1 = rs.getString("apellido1");
			String apellido2 = rs.getString("apellido2");
			String ciudad = rs.getString("ciudad");
			float comision = rs.getFloat("comision");
			Comercial c = new Comercial(id, nombre, apellido1, apellido2, ciudad, comision);
			comerciales.add(c);
		}
		return comerciales;
	}

	public String obtenerInfoBaseDatos() throws SQLException {
		String result = "";
		String sql = "SELECT table_name, column_name, data_type FROM information_schema.columns WHERE table_schema = 'ventas'";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		String table = "";
		while (rs.next()) {
			if (!table.equals(rs.getString("table_name"))) {
				table = rs.getString("table_name");
				result += table + ":\n";
			}
			result += "\t" + rs.getString("column_name") + " [" + rs.getString("data_type") + "]\n";
		}
		return result;
	}
}
