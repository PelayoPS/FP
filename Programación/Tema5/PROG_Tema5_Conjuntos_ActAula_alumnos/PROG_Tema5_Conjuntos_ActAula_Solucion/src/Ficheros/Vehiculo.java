package Ficheros;

import java.util.LinkedList;

public class Vehiculo implements Comparable<Vehiculo>{
	private String matricula;
	private String dni;
	private String nombre;
	// añadir atributos necesarios
	private LinkedList<Paquete> paquetes;
	private int cp = -1;
	
	
	// completar constructor
	public Vehiculo(String matricula, String dni, String nombre) {
		this.matricula = matricula;
		this.dni = dni;
		this.nombre = nombre;
		paquetes = new LinkedList<Paquete>();
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public int getCp() {
		return cp;
	}

	public String getMatricula() {
		return matricula;
	}

	public void añadirPaquete(Paquete p) {
		paquetes.add(p);
	}


	// mostrar los datos del vehiculo asi como los
	// paquetes asignados si los tuviera
	public void mostrarVehiculo(){
		System.out.println("Vehiculo: [matricula=" + matricula + ", dni=" +
		 dni + ", nombre=" + nombre + ", cpAsignado=" + cp + "]");
		if (paquetes.size()>0) {
			for (Paquete paquete : paquetes) {
				System.out.println('\t'+paquete.toString());
			}
		}
	}

	public LinkedList<Paquete> getPaquetes() {
		return paquetes;
	}

	@Override
	public int compareTo(Vehiculo o) {
		return matricula.compareTo(o.matricula);
	}
	
	
}
