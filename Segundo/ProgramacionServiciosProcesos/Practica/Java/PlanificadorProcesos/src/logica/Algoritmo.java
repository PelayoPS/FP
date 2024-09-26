package logica;
/**
 * Clase algoritmo de la que hereda cada uno de los algoritmos
 */
public interface Algoritmo {
	static final char usedTime = '\u2588';
	static final char wastedTime = '\u2591';

	/**
	 * Método execute que tiene cada uno de los algoritmos
	 * Devuelve una matriz con los resultados
	 */
	String[] execute(Proceso[] procesos);

}
