package Modelo;

import Excepciones.PersistenciaExcepcion;

public class Libro implements Serialize{
	
	private int codigo;
	private String ISBN;
	private String titulo;
	private String autor;

	public Libro() {
	}

	public Libro(int codigo, String ISBN, String titulo, String autor) {
		this.codigo = codigo;
		this.ISBN = ISBN;
		this.titulo = titulo;
		this.autor = autor;
	}

	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getCodigo() {
		return codigo;
	}

	public String toString() {
		return "Libro=[codigo=" + codigo + ", ISBN=" + ISBN + ", titulo=" + titulo + ", autor=" + autor + "]";
	}

	@Override
	public String serialize(Object obj) {
		Libro libro = (Libro) obj;
		// 2@codigo@ISBN@titulo@autor
		return "2@" + libro.getCodigo() + "@" + libro.getISBN() + "@" + libro.getTitulo() + "@" + libro.getAutor();
	}

	@Override
	public Object serialize(String linea) throws PersistenciaExcepcion {
		String[] trozos = linea.split("@");
		if (trozos.length != 5) {
			throw new PersistenciaExcepcion("Error: Registro de libro incorrecto");
		}
		try {
			int codigo = Integer.parseInt(trozos[1]);
			String ISBN = trozos[2];
			String titulo = trozos[3];
			String autor = trozos[4];
			return new Libro(codigo, ISBN, titulo, autor);
		} catch (Exception e) {
			throw new PersistenciaExcepcion("Error en formato de número de libro");
		}
	}



	
	
	
	
}
