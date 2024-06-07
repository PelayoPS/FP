package envios;

/**
 * Clase que representa un envío
 * 
 * @author Pelayo Palacio Suárez
 * @version 1.1
 */
public class Envios {
	// Constantes
	private final double NORMAL = 3;
	private final double ESPECIAL = 5;
	private final double URGENTE = 10;

	// Atributos
	private Persona destinatario;
	private Persona remitente;
	private String tipo;

	// Constructores

	/**
	 * Constructor de la clase Envios
	 * 
	 * @param Persona : d destinatario
	 * @param Persona : r remitente
	 * @param String  : t tipo de envío
	 */
	public Envios(Persona destinatario, Persona remitente, String tipo) {
		setDestinatario(destinatario);
		setRemitente(remitente);
		setTipo(tipo);
	}

	// Métodos

	/**
	 * Método que calcula el importe del envío
	 * 
	 * @param double : peso del envío
	 * @return double : importe del envío
	 */
	public double calculaImporte(double peso) {
		double total = 0;
		switch (getTipo()) {
			case "normal":
				total = NORMAL * peso;
				break;
			case "especial":
				total = ESPECIAL * peso;
				break;
			case "urgente":
				total = URGENTE * peso;
				break;
			default:
				break;
		}
		return total;
	}

	// Getters

	/**
	 * Método que devuelve el destinatario
	 * 
	 * @return Persona : destinatario
	 */
	public Persona getDestinatario() {
		return destinatario;
	}

	/**
	 * Método que devuelve el remitente
	 * 
	 * @return Persona : remitente
	 */
	public Persona getRemitente() {
		return remitente;
	}

	/**
	 * Método que devuelve el tipo de envío
	 * 
	 * @return String : tipo de envío
	 */
	public String getTipo() {
		return tipo;
	}

	// Setters
	
	/**
	 * Método que establece el destinatario
	 * 
	 * @param Persona : destinatario
	 */
	public void setDestinatario(Persona destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * Método que establece el remitente
	 * 
	 * @param Persona : remitente
	 */
	public void setRemitente(Persona remitente) {
		this.remitente = remitente;
	}

	/**
	 * Método que establece el tipo de envío
	 * 
	 * @param String : tipo de envío
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	// toString

	/**
	 * Método que devuelve el destinatario
	 * 
	 * @return String : envío en formato String
	 */
	public String toString() {
		return "DATOS DESTINATARIO\n" + getDestinatario().toString()
				+ "DATOS REMITENTE\n" + getRemitente().toString()
				+ "TIPO ENVIO: " + getTipo();
	}

}
