package Logica;

import java.sql.SQLException;
import java.util.List;

import Persistencia.GestorJDBC;

public class Ventas {

	GestorJDBC gestor;
	
	public Ventas() throws ClassNotFoundException, SQLException {
		gestor=new GestorJDBC();
	}
	public boolean consulta1() throws SQLException {
		return gestor.crearTablaLogin();
	}

	public int consulta2(String[] nombres, String[] claves) throws SQLException {
		return gestor.insertarDatosLogin(nombres, claves);
	}

	public String consulta3(String nombre, String pass) throws SQLException {
		return gestor.validarUsuario(nombre, pass);
	}	
	public String consulta4() throws SQLException {
		String result = "";
		List<Pedido> pedidos = gestor.obtenerDatosPedidos();
		for (Pedido pedido : pedidos) {
			result += pedido.toString() + "\n";
		}
		return result;

	}


	public String consulta5() throws SQLException {
		String result = "";
		List<Cliente> clientes = gestor.obtenerClientesConPedidos();
		for (Cliente cliente : clientes) {
			result += cliente.toString() + "\n";
		}
		return result;
	}

	public String consulta6() throws SQLException {
		String result = "";
		Pedido pedido = gestor.obtenerPedidoMayorValor();
		result += pedido.toString();
		return result;
	}

	public String consulta7() throws SQLException {
		String result = "";
		List<Comercial> comerciales = gestor.obtenerComercialesSinPedidos();
		for (Comercial comercial : comerciales) {
			result += comercial.toString() + "\n";
		}
		return result;
	}

	public String consulta8() throws SQLException {
		return gestor.obtenerInfoBaseDatos();
	}

}
