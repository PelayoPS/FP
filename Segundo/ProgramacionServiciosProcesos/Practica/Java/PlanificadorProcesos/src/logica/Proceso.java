package logica;

public class Proceso {

	private String name;
	private int t_llegada;
	private int t_ejecucion;

	/**
	 * Asigna los parámetros al proceso
	 * @param String : name
	 * @param int : t_llegada
	 * @param int : t_ejecuccion
	 */
	public Proceso(String name, int t_llegada, int t_ejecucion) {
		this.name = name;
		this.t_llegada = t_llegada;
		this.t_ejecucion = t_ejecucion;
	}

	// getters y setters

	/**
	 * Devuelve el nombre del proceso
	 * @return nombre del proceso
	 */
	public String getName() {
		return name;
	}

	/**
	 * Devuelve el tiempo de llegada del proceso
	 * @return tiempo de llegada del proceso
	 */
	public int getT_llegada() {
		return t_llegada;
	}

	/**
	 * Devuelve el tiempo de ejecución del proceso
	 * @return tiempo de ejecucion del proceso
	 */
	public int getT_Ejecucion() {
		return t_ejecucion;
	}

}
