package Logica;

import java.util.ArrayList;
import java.util.List;

import Excepciones.LogicaExcepcion;
import Interfaz.Loggerfichero;
import Modelo.*;
import Persistencia.GestorPersistencia;

public class Biblioteca {

	List<Prestamo> prestamos;
	List<Socio> socios;
	List<Libro> libros;

	Loggerfichero log = Loggerfichero.getInstance();

	public Biblioteca(String nombreFichero) {
		this.prestamos = new ArrayList<Prestamo>();
		this.socios = new ArrayList<Socio>();
		this.libros = new ArrayList<Libro>();
		cargarDatos(nombreFichero);
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public List<Socio> getSocios() {
		return socios;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void cargarDatos(String nombreFichero) {
		try {
			GestorPersistencia gp = new GestorPersistencia(this);
			gp.cargarTxt(nombreFichero);
		} catch (Exception e) {
			log.writeSmg("Error al cargar los datos: " + e.toString());
			System.out.println("Error al cargar los datos: " + e.getMessage());
			System.out.println("El programa se cerrará");
			System.exit(0);
		}
	}

	public String eliminarSociosConMuchosPrestamos() {

		socios.stream().filter(s -> s.getPrestamos().size() > 3).forEach(s -> {
			socios.remove(s);
			prestamos.stream().filter(p -> p.getnCliente() == s.getnSocio()).forEach(p -> {
				prestamos.remove(p);
			});
		});
		return listarSocios();
	}

	public String listarSocios() {
		// Socio=[nSocio=1, apellidos=apellidos1, nombre=nombre1, telefono=123456789]
		String lista = "";
		for (Socio s : socios) {
			lista += s.toString() + "\n";
		}
		return lista;
	}

	public String listarLibros() {
		// Libro=[codigo=1, ISBN=ISBN1, titulo=titulo1, autor=autor1]
		String lista = "";
		for (Libro l : libros) {
			lista += l.toString() + "\n";
		}
		return lista;
	}

	public String Prestar(int socio, int codLibro) throws LogicaExcepcion {
		// el socio debe tener menos de 4 libros
		Socio s = socios.stream().filter(soc -> soc.getnSocio() == socio).findFirst().orElse(null);
		if (s == null) {
			throw new LogicaExcepcion("Error: Socio no encontrado");
		}
		if (s.getPrestamos().size() >= 3) {
			throw new LogicaExcepcion("Error: Socio con mas de 3 prestamos");
		}
		// el libro debe existir
		Libro l = libros.stream().filter(lib -> lib.getCodigo() == codLibro).findFirst().orElse(null);
		if (l == null) {
			throw new LogicaExcepcion("Error: Libro no encontrado");
		}
		// el libro no debe estar prestado
		if (prestamos.stream().filter(p -> p.getCodLibro() == codLibro).count() > 0) {
			throw new LogicaExcepcion("Error: Libro ya prestado");
		}
		// se realiza el prestamo
		Prestamo p = new Prestamo(socio, codLibro);
		prestamos.add(p);
		s.getPrestamos().add(p);
		return "Prestamo realizado";
	}

	public String EliminarPrestamo(int socio, int codLibro) throws LogicaExcepcion {
		// el prestamo debe existir
		if (prestamos.stream().filter(p -> p.getnCliente() == socio && p.getCodLibro() == codLibro).count() == 0) {
			throw new LogicaExcepcion("Error: Prestamo no encontrado");
		}
		// el socio debe existir
		if (socios.stream().filter(s -> s.getnSocio() == socio).count() == 0) {
			throw new LogicaExcepcion("Error: Socio no encontrado");
		}
		// el libro debe existir
		if (libros.stream().filter(l -> l.getCodigo() == codLibro).count() == 0) {
			throw new LogicaExcepcion("Error: Libro no encontrado");
		}
		// se elimina el prestamo
		prestamos.stream().filter(p -> p.getnCliente() == socio && p.getCodLibro() == codLibro).forEach(p -> {
			prestamos.remove(p);
		});
		// se elimina el prestamo del socio
		socios.stream().filter(s -> s.getnSocio() == socio).forEach(s -> {
			s.getPrestamos().stream().filter(p -> p.getCodLibro() == codLibro).forEach(p -> {
				s.getPrestamos().remove(p);
			});
		});
		return "Prestamo eliminado";
	}

	public String ListarPrestamos(int socio) {
		// Prestamo=[codLibro=1, nCliente=1]
		String lista = "";
		for (Prestamo p : prestamos) {
			if (p.getnCliente() == socio) {
				lista += p.toString() + "\n";
			}
		}
		return lista;

	}

	public void guardarFicheros() {
		try {
			GestorPersistencia gp = new GestorPersistencia(this);
			gp.grabarTxt();
		} catch (Exception e) {
			log.writeSmg("Error al guardar los datos: " + e.toString());
		}

	}

}
