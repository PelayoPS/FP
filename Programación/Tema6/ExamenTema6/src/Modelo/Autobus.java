package Modelo;

import java.util.ArrayList;
import java.util.List;

import Excepciones.FormatoExcepcion;

public class Autobus {
	// precio base diario constante 100.0
	private static final double precioDiarioBase = 100.0;

	private static int diasMes[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private Matricula matricula;
	private int numPlazas;
	private double precioDia;
	private double precioKm;

	// calendario; Arraylist de enteros
	private List<Integer> calendario = new ArrayList<Integer>();

	public Autobus(String m, int numPlazas, double precioKm) throws FormatoExcepcion {
		this.matricula = new Matricula(m);
		this.numPlazas = numPlazas;
		this.precioDia = precioDiarioBase;
		this.precioKm = precioKm;

	}

	/**
	 * transforma formato dd//mm//aaaa
	 * a un entero [1...365]
	 * utilizando el número de días por mes
	 * 
	 * @param fecha
	 * @return
	 * @throws FormatoExcepcion
	 */
	public static int CalcularDia(String fecha) throws FormatoExcepcion {
		// si fecha no está en formato dd/mm/aaaa lanza excepción
		// si la fecha no tiene los valores en los rangos de meses salta error
		// mes [1-12]
		// dia [1-31]
		if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
			throw new FormatoExcepcion("Formato de fecha incorrecto");
		}

		String[] partes = fecha.split("/");
		int dia = Integer.parseInt(partes[0]);
		int mes = Integer.parseInt(partes[1]);
		// indica un mensaje según el tipo de error
		if (mes < 1 || mes > 12) {
			throw new FormatoExcepcion("Mes incorrecto");
		}
		if (dia < 1 || dia > diasMes[mes - 1]) {
			throw new FormatoExcepcion("Día incorrecto");
		}
		int diaAnio = 0;
		for (int i = 0; i < mes - 1; i++) {
			diaAnio += diasMes[i];
		}
		diaAnio += dia;
		return diaAnio;
	}

	public void alquilar(String fechaAlquiler, int numDiasAlquiler) throws FormatoExcepcion {
		int dia = CalcularDia(fechaAlquiler);
		for (int i = 0; i < numDiasAlquiler; i++) {
			// si está repetido avisar con ExamenException
			if (calendario.contains(dia + i)) {
				throw new FormatoExcepcion("Fecha no disponible");
			}
			calendario.add(dia + i);
		}
		
	}

	public boolean estaDisponible(String fecha, int numDias) throws FormatoExcepcion {
		int dia = CalcularDia(fecha);
		for (int i = 0; i < numDias; i++) {
			if (calendario.contains(dia + i)) {
				return false;
			}
		}
		return true;
	}

	public double getPrecioDia() {
		return precioDia;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public double getPrecioKm() {
		return precioKm;

	}

	/**
	 * muestra los datos del autobus
	 * Autobus [matricula: valor, plaza: valor, preciodia: valor, precioKm: valor]
	 */
	public String toString() {
		return "Autobus [" +
				"matricula: " + matricula + ", " +
				"plaza: " + numPlazas + ", " +
				"preciodia: " + precioDia + ", " +
				"precioKm: " + precioKm + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Autobus) {
			Autobus a = (Autobus) obj;
			return this.matricula.equals(a.matricula);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return matricula.hashCode();
	}

}
