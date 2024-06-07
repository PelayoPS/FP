package Modelo;

import Excepciones.FormatoExcepcion;

public class Alquiler {
	private Autobus autobus;
	private String nifCliente;
	private String fechaAlquiler;
	private int numDiasAlquiler;
	private int kms;

	public Alquiler(Autobus a, String nifCliente, String fechaAlquiler, int numDiasAlquiler, int km)  throws
	    FormatoExcepcion {
		// si fecha no está en formato dd/mm/aaaa lanza excepción
		if (!fechaAlquiler.matches("\\d{2}/\\d{2}/\\d{4}")) {
			throw new FormatoExcepcion("Formato de fecha incorrecto");
		}
		this.autobus = a;
		this.nifCliente = nifCliente;
		this.fechaAlquiler = fechaAlquiler;
		this.numDiasAlquiler = numDiasAlquiler;
		this.kms = km;
	}

	/**
	 * Importe = (precio base diario del bus * numDias alquiler) + (precioKm * numKm)
	 * @return
	 */
	public double calcularImporte() {
		return (autobus.getPrecioDia() * numDiasAlquiler) + (autobus.getPrecioKm() * kms);		
	}

	public Autobus getAutobus() {
		return autobus;
	}

	public String getNifCliente() {
		return nifCliente;
	}

	public String getFechaAlquiler() {
		return fechaAlquiler;
	}

	public int getNumDiasAlquiler() {
		return numDiasAlquiler;
	}

}
