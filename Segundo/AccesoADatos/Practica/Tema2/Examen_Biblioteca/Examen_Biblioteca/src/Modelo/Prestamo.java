package Modelo;

import Excepciones.PersistenciaExcepcion;

public class Prestamo implements Serialize {

	private int codLibro;
	private int nCliente;

	public Prestamo() {
	}

	public Prestamo(int codLibro, int nCliente) {
		this.codLibro = codLibro;
		this.nCliente = nCliente;
	}

	public int getCodLibro() {
		return codLibro;
	}

	public int getnCliente() {
		return nCliente;
	}

	public String toString() {
		return "Prestamo=[codLibro=" + codLibro + ", nCliente=" + nCliente + "]";
	}

	@Override
	public String serialize(Object obj) {
		Prestamo prestamo = (Prestamo) obj;
		// 3@numeroSocio@codigoLibro
		return "3@" + prestamo.getnCliente() + "@" + prestamo.getCodLibro();
	}

	@Override
	public Object serialize(String linea) throws PersistenciaExcepcion {
		String[] trozos = linea.split("@");
		if (trozos.length != 3) {
			throw new PersistenciaExcepcion("Error: Registro de prestamo incorrecto");
		}
		try {
			int nCliente = Integer.parseInt(trozos[1]);
			int codLibro = Integer.parseInt(trozos[2]);
			return new Prestamo(codLibro, nCliente);
		} catch (Exception e) {
			throw new PersistenciaExcepcion("Error: Registro de prestamo incorrecto");
		}
	}
	
	
}
