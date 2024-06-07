package Modelo;


import Excepciones.FormatoExcepcion;

public class Matricula {
	private String cadena;
	
	public Matricula(String m) throws FormatoExcepcion{
			comprobarMatricula(m);
			this.cadena = m;
	}

	private void comprobarMatricula(String m) throws FormatoExcepcion {
		if (!m.matches("\\d{4}-[A-Z]{3}")) {
			throw new FormatoExcepcion("Formato de matrícula incorrecto");
		}
		
	}

	@Override
	public String toString() {
		return cadena;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Matricula) {
			Matricula m = (Matricula) obj;
			return this.cadena.equals(m.cadena);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return cadena.hashCode();
	}


	
	
	
}
