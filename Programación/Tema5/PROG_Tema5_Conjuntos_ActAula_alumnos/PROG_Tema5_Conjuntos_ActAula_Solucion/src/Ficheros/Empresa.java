package Ficheros;

import java.util.HashSet;
import java.util.TreeSet;

public class Empresa {
	private HashSet<Paquete> paquetes;
	private TreeSet<Vehiculo> vehiculos;

	public Empresa() {
		paquetes = new HashSet<Paquete>();
		//crea el treeset con el comparador por matricula
		vehiculos = new TreeSet<Vehiculo>();
		

	}

	// añade un paquete al conjunto siempre que cumpla que
	// no existe un paquete con el mismo codigo ya incluido
	public void añadirPaquete(Paquete p) {
		for (Paquete paquete : paquetes) {
			if (paquete.getCodigo() == p.getCodigo()) {
				System.err.println("Error: Paquete " + p.getCodigo() + " ya incluido");
				return;
			}
		}
		paquetes.add(p);
		System.out.println("Paquete " + p.getCodigo() + " añadido correctamente");
	}

	// añade un vehiculo al conjunto siempre que cumpla que
	// no existe un vehiculo con la misma matricula ya incluido
	public void añadirVehiculo(Vehiculo v) {
		if (!vehiculos.add(v)) {
			System.err.println("Error: Vehiculo " + v.getMatricula() + " ya incluido");
			return;
		} else {
			System.out.println("Vehiculo " + v.getMatricula() + " añadido correctamente");
		}
	}


	// metodo que muestra el numero total de paquetes asi
	// como el detalle de cada uno de ellos
	public void mostrarPaquetes() {
		int totalPaquetes = paquetes.size();
		System.out.println("Total de paquetes: " + totalPaquetes);
		System.out.println("Detalle de paquetes: ");
		for (Paquete paquete : paquetes) {
			System.out.println(paquete.toString());
		}
	}

	// metodo que muestra el numero total de vehiculos asi
	// como el detalle de cada uno de ellos

	public void mostrarVehiculos() {
		int totalVehiculos = vehiculos.size();
		System.out.println("Total de vehiculos: " + totalVehiculos);
		System.out.println("Detalle de vehiculos: ");
		for (Vehiculo vehiculo : vehiculos) {
			vehiculo.mostrarVehiculo();
		}
	}

	/*
	 * metodo que recibe los cps del dia a repartir entre los
	 * vehiculos
	 * Pasos:
	 * Para cada cp
	 * buscar vehiculo disponible ( sin asignación de cp)
	 * Añadir todos los paquetes que tengas el cp seleccionado
	 */
	public void repartirPaquetes(int cps[]) {
		for (int cp : cps) {
			boolean asignado = false;
			for (Vehiculo vehiculo : vehiculos) {
				if (!asignado && vehiculo.getCp() == -1) {
					vehiculo.setCp(cp);
					for (Paquete paquete : paquetes) {
						if (paquete.getCp() == cp) {
							vehiculo.añadirPaquete(paquete);
						}
					}
					asignado = true;
				}
			}
		}
	}

	/*
	 * muestra el conjunto de vehiculos y la distribución
	 * de los paquetes asignados
	 */
	public void mostrarReparto() {
		System.out.println("Detalle de reparto: ");
		for (Vehiculo vehiculo : vehiculos) {
			vehiculo.mostrarVehiculo();
		}

	}

}
