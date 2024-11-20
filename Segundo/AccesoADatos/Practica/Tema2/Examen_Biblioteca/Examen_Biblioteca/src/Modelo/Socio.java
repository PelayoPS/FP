package Modelo;

import java.util.ArrayList;
import java.util.List;

import Excepciones.PersistenciaExcepcion;

public class Socio implements Serialize{

	private int nSocio;
	private String nombre;
	private String apellidos;
	private String telefono;
	private List<Prestamo> prestamos;

	public Socio() {
		this.prestamos = new ArrayList<Prestamo>();
	}

	public Socio(int nSocio, String nombre, String apellidos, String telefono) {
		this.nSocio = nSocio;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.prestamos = new ArrayList<Prestamo>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public int getnSocio() {
		return nSocio;
	}

	public String toString() {
		return "Socio=[nSocio=" + nSocio + ", apellidos=" + apellidos + ", nombre=" + nombre + ", telefono=" + telefono
				+ "]";
	}

	@Override
	public String serialize(Object obj) {
		Socio s = (Socio) obj;
		// 1@nSocio@apellidos@nombre@telefono
		return "1@" + s.getnSocio() + "@" + s.getApellidos() + "@" + s.getNombre() + "@" + s.getTelefono();
	}

	@Override
	public Object serialize(String linea) throws PersistenciaExcepcion {
		String[] trozos = linea.split("@");
		if (trozos[0].equals("1")) {
			try {
				int nSocio = Integer.parseInt(trozos[1]);
				String apellidos = trozos[2];
				String nombre = trozos[3];
				String telefono = trozos[4];
				return new Socio(nSocio, nombre, apellidos, telefono);
			} catch (Exception e) {
				throw new PersistenciaExcepcion("Error en formato de número de socio");
			}
		}
		return null;
	}

	
}
