package Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Excepciones.LogicaExcepcion;
import Excepciones.PersistenciaExcepcion;
import Interfaz.Loggerfichero;
import Logica.Biblioteca;
import Modelo.Libro;
import Modelo.Prestamo;
import Modelo.Socio;

public class GestorPersistencia {

	private Loggerfichero log;
	private Biblioteca biblioteca;

	public GestorPersistencia(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
		this.log = Loggerfichero.getInstance();
	}

	public void cargarTxt(String fichero) throws IOException {
		fichero = "src\\Datos\\" + fichero;
		BufferedReader buffer = null;
		File f = new File(fichero);
		// Si el fichero no existe lanzamos una excepción
		if (!f.exists()) {
			throw new FileNotFoundException("Fichero no encontrado");
		}

		try {

			buffer = new BufferedReader(new FileReader(fichero));
			String linea = "";
			int contadorLineas = 0;
			// No tiene cabecera
			while (buffer.ready()) {
				linea = buffer.readLine();
				contadorLineas++;
				// Método a parte para tratar la línea
				// Error porque produce una excepción. Hay que tratarla para que siga leyendo
				try {
					tratarLinea(linea);
				} catch (Exception e) {
					log.writeSmg("Error en línea " + contadorLineas + ": " + e.toString() + "\n");
				}

			}
		} catch (FileNotFoundException e) {
			log.writeSmg("Fichero no encontrado\n");
		} catch (IOException e) {
			// Excepción propia de java
			log.writeSmg("Error entrada salida: Error en la lectura del fichero\n");
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				log.writeSmg("Error al cerrar el programa\n");
			}
		}

	}

	private void tratarLinea(String linea) throws PersistenciaExcepcion, LogicaExcepcion {
		String[] trozos = linea.split("@");
		switch (trozos[0]) {
			case "1":
				// Tratando Socio
				if (trozos.length != 5) {
					throw new PersistenciaExcepcion("Error: Registro de socio incorrecto");
				}
				try {
					Socio socio = new Socio();
					socio.serialize(linea);
					// si el socio ya existe no se añade
					if (this.biblioteca.getSocios().stream().filter(s -> s.getnSocio() == socio.getnSocio())
							.count() == 0) {
						this.biblioteca.getSocios().add(socio);
					}
				} catch (Exception e) {
					throw new PersistenciaExcepcion("Error en formato de número de socio");
				}
				break;

			case "2":
				// Tratando Libro
				if (trozos.length != 5) {
					throw new PersistenciaExcepcion("Error: Registro de libro incorrecto");
				}
				try {
					Libro libro = new Libro();
					libro.serialize(linea);
					// si el libro ya existe no se añade
					if (this.biblioteca.getLibros().stream().filter(l -> l.getCodigo() == libro.getCodigo())
							.count() == 0) {
						this.biblioteca.getLibros().add(libro);
					}
				} catch (Exception e) {
					throw new PersistenciaExcepcion("Error en formato de número de libro");
				}
				break;

			case "3":
				// Tratando Prestamo
				if (trozos.length != 3) {
					throw new PersistenciaExcepcion("Error: Registro de préstamo incorrecto");
				}
				try {
					Prestamo prestamo = new Prestamo();
					prestamo.serialize(linea);
					// Buscar socio
					Socio socio = this.biblioteca.getSocios().stream()
							.filter(s -> s.getnSocio() == prestamo.getnCliente()).findFirst().orElse(null);
					if (socio == null) {
						throw new PersistenciaExcepcion("Error: Socio no encontrado en préstamo");
					}
					// Buscar libro
					Libro libro = this.biblioteca.getLibros().stream()
							.filter(l -> l.getCodigo() == prestamo.getCodLibro()).findFirst().orElse(null);
					if (libro == null) {
						throw new PersistenciaExcepcion("Error: Libro no encontrado en préstamo");
					}
					biblioteca.Prestar(prestamo.getnCliente(), prestamo.getCodLibro());

				} catch (Exception e) {
					throw new PersistenciaExcepcion("Error en formato de número en préstamo");
				}
				break;

			default:
				// Error registro de ningún tipo
				throw new PersistenciaExcepcion("Error registro no identificado");
		}
	}

	public void grabarTxt() throws IOException {
		String fichero = "src\\Datos\\biblioteca.txt";

		BufferedWriter buffer = null;
		File f = new File(fichero);
		// Si el fichero no existe lanzamos una excepción
		if (!f.exists()) {
			throw new FileNotFoundException("Fichero no encontrado");
		}

		try {

			FileWriter file = new FileWriter(fichero);
			buffer = new BufferedWriter(file);

			// Grabar socios
			for (Socio s : this.biblioteca.getSocios()) {
				String linea = s.serialize(s) + "\n";
				buffer.write(linea);
			}
			// Grabar libros
			for (Libro l : this.biblioteca.getLibros()) {
				String linea = l.serialize(l) + "\n";
				buffer.write(linea);
			}
			// Grabar los prestamos
			for (Socio s : this.biblioteca.getSocios()) {
				for (Prestamo p : s.getPrestamos()) {
					String linea = p.serialize(p) + "\n";
					buffer.write(linea);
				}
			}

		} catch (IOException e) {
			log.writeSmg("Error E/S en fichero de salida\n");
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				log.writeSmg("Error al cerrar el fichero");
			}
		}

	}

}
