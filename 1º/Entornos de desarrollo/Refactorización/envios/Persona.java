package envios;

/**
 * Clase que representa una persona
 * 
 * @autor Pelayo Palacio Suárez
 * @version 1.1
 */
public class Persona {
	// Atributos
	private String dni;
	private String nombre;

	// Constructores

	/**
	 * Constructor de la clase Persona
	 * 
	 * @param String : dni
	 * @param String : nombre
	 */
	public Persona(String dni, String nombre) {
		setDni(dni);
		setNombre(nombre);
	}

	// Getters

	/**
	 * Método que devuelve el dni de la persona
	 * @return String : dni
	 */
	public String getDni() {
		return this.dni;
	}

	/**
	 * Método que devuelve el nombre de la persona
	 * @return String : nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	// Setters

	/**
	 * Método que establece el dni de la persona
	 * @param String : dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Método que establece el nombre de la persona
	 * @param String : nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Métodos

	/**
	 * Método que devuelve el dni y el nombre de la persona
	 * @return String : dni y nombre
	 */
	public String toString() {
		return "DNI: " + this.dni + "\nNOMBRE: " + this.nom + "\n";
	}

}
