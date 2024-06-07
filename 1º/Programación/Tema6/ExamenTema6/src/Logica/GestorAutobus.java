package Logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import Excepciones.ExamenExcepcion;
import Excepciones.FormatoExcepcion;
import Modelo.Alquiler;
import Modelo.Autobus;
import Modelo.Cliente;
import Modelo.Matricula;

public class GestorAutobus {

	// hashmap autobuses con matricula como key
	private Map<Matricula, Autobus> autobuses;
	// treeset clientes
	private Set<Cliente> clientes;
	// arraylist alquileres
	private List<Alquiler> alquileres;

	public GestorAutobus() {
		this.autobuses = new HashMap<Matricula, Autobus>();
		this.clientes = new TreeSet<Cliente>();
		this.alquileres = new ArrayList<Alquiler>();
	}

	public String generarFactura(String nif) throws ExamenExcepcion {
		// si el nif no existe notificar con error
		if (!clientes.contains(new Cliente(nif, ""))) {
			throw new ExamenExcepcion(
					"Cliente no existe");
		}
		// generar un listado de todos los alquileres por nif
		// calculando el importe total de todos los alquileres
		double total = alquileres.stream()
				.filter(a -> a.getNifCliente().equals(nif))
				.mapToDouble(a -> a.calcularImporte())
				.sum();

		return "Cliente: " + nif + " Importe total: " + total;

	}

	public String autobusesDisponibles(String fecha, int numDias, int numPlazas)
			throws FormatoExcepcion {
		// si fecha incorrecta lanza excepción
		if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
			throw new FormatoExcepcion("Formato de fecha incorrecto");
		}

		// generar un listado de todos los autobuses disponibles
		// para la fecha y número de días indicados
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Autobuses disponibles\n");
		autobuses.values()
				.stream()
				.filter(a ->
				{
					try {
						return a.estaDisponible(fecha, numDias) && a.getNumPlazas() >= numPlazas;
					} catch (FormatoExcepcion e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return false;
				})
				.forEach(a -> {
					stringBuilder.append(a.toString());
					stringBuilder.append("\n");
				});
		return stringBuilder.toString();
	}

	public void alquilarAutocar(String matricula, String nif, String fecha,
			int numDias, int numKm) throws ExamenExcepcion, FormatoExcepcion {
		// si el autobus no existe o no está disponible para esa fecha
		// notificar con error
		if (!autobuses.containsKey(new Matricula(matricula))) {
			throw new ExamenExcepcion("Autobus no existe");
		}
		if (!autobuses.get(new Matricula(matricula)).estaDisponible(fecha, numDias)) {
			throw new ExamenExcepcion("Autobus no disponible");
		}
		// si el cliente no existe notificar con error
		if (!clientes.contains(new Cliente(nif, ""))) {
			throw new ExamenExcepcion("Cliente no existe");
		}

		// añadir un nuevo alquiler
		alquileres.add(new Alquiler(autobuses.get(new Matricula(matricula)), nif, fecha, numDias, numKm));
		// alquilar autobus
		autobuses.get(new Matricula(matricula)).alquilar(fecha, numDias);

	}

	public String listarTodosLosAutobuses() {
		// generar un listado de todos los autobuses
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(
				"Autobuses " + autobuses.values().size());
		strBuilder.append("\n");
		autobuses.values()
				.stream()
				.forEach(a -> {
					strBuilder.append(a.toString());
					strBuilder.append("\n");
				});
		return strBuilder.toString();
	}

	public String listarAlquileresAutobus(String matricula) throws ExamenExcepcion, FormatoExcepcion {
		// si el autobus no existe notificar con error
		if (!autobuses.containsKey(
				new Matricula(matricula))) {
			throw new ExamenExcepcion(
					"Autobus no existe");
		}
		StringBuilder stringBuilder = new StringBuilder();
		alquileres.stream()
				.filter(a -> a.getAutobus().getMatricula().toString().equals(matricula))
				.forEach(a -> {
					stringBuilder.append(
							"Cliente: " + a.getNifCliente() + " Fecha: " + a.getFechaAlquiler() + " Dias: "
									+ a.getNumDiasAlquiler() + "\n");
				});
		return stringBuilder.toString();
	}

	public String listarAlquileresCliente(String nif) throws ExamenExcepcion {
		// si el nif no existe avisa con un error
		if (!clientes.contains(new Cliente(nif, ""))) {
			throw new ExamenExcepcion("Cliente no existe");
		}
		StringBuilder stringBuilder = new StringBuilder();
		alquileres.stream()
				.filter(a -> a.getNifCliente().equals(nif))
				.forEach(a -> {
					stringBuilder.append(
							"Autobus: " + a.getAutobus().getMatricula() + " Fecha: " + a.getFechaAlquiler() + " Dias: "
									+ a.getNumDiasAlquiler() + "\n");
				});
		return stringBuilder.toString();
	}

	/**
	 * Recibe un autobus y si ya está almacenado notifica de error, sino
	 * lo añade
	 * 
	 * @param a autobus a añadir
	 * @throws ExamenExcepcion
	 */
	public void addAutobus(Autobus a) throws ExamenExcepcion {
		// si el hashmap contiene la matricula del autobus lanza la excepción
		// sino, lo añade
		if (autobuses.containsKey(a.getMatricula())) {
			throw new ExamenExcepcion("Autobus ya existe");
		}
		autobuses.put(a.getMatricula(), a);

	}

	/**
	 * Recibe un cliente si el nif del cliente se encuentra almacenado notifica con
	 * un error
	 * 
	 * @param c cliente a añadir
	 * @throws ExamenExcepcion si el cliente ya está dentro
	 */
	public void addCliente(Cliente c) throws ExamenExcepcion {
		// si la lista contiene un cliente así lanza la excepción
		// sino, lo añade
		if (clientes.contains(c)) {
			throw new ExamenExcepcion("Cliente ya existe");
		}
		clientes.add(c);
	}

}
