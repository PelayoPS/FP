package src;

/**
 * Clase que contiene los métodos para trabajar con vectores
 * @author Pelayo Palacio Suárez
 */
public class Main {

	/**
	 * Main method that tests the methods of this class
	 * 
	 * @param String[] args
	 */
	public static void main(String[] args) {
		int[] relieve = { 2, 4, 3, 5, 5, 5, 5, 7, 9, 8, 6, 5, 7, 8, 8, 8, 10, 9, 7, 6, 4, 3, 4, 4, 4, 2, 0 };
		System.out.println("Número de valles: " + numeroValle(relieve));
		System.out.println("Valor del pico más alto: " + valorPicoMasAlto(relieve));
		System.out.println("Número de mesetas: " + numeroMesetas(relieve));

	}

	/**
	 * Método que devuelve true si el vector es un valle, false en caso contrario
	 * Un vector es un valle si existe un elemento que es menor que sus dos vecinos
	 * 
	 * @param int[] : v vector de enteros
	 * @param int   pos : posición del vector
	 * @return boolean : true si es un valle, false en caso contrario
	 */
	public static boolean esValle(int[] v, int pos) {
		boolean esValle = false;// variable de control
		if (pos > 0 && pos < v.length - 1) {// bucle sobre el vector
			if (v[pos] < v[pos - 1] && v[pos] < v[pos + 1]) {// condición de valle
				esValle = true;// si es valle, cambiamos el valor de la variable de control
			}
		}
		return esValle;// devolvemos el valor de la variable de control
	}

	/**
	 * Método que devuelve true si el vector es un pico, false en caso contrario
	 * Un vector es un pico si existe un elemento que es mayor que sus dos vecinos
	 * 
	 * @param int[] : v vector de enteros
	 * @param int   pos : posición del vector
	 * @return boolean : true si es un pico, false en caso contrario
	 */
	public static boolean esPico(int[] v, int pos) {
		boolean esPico = false;// variable de control
		if (pos > 0 && pos < v.length - 1) {// condición de seguridad
			if (v[pos] > v[pos - 1] && v[pos] > v[pos + 1]) {// condición de pico
				esPico = true;// si es pico, cambiamos el valor de la variable de control
			}
		}
		return esPico;// devolvemos el valor de la variable de control
	}

	/**
	 * Método que devuelve el número de valles que hay en el vector
	 * 
	 * @param int[] : v vector de enteros
	 * @return int : número de valles
	 */
	public static int numeroValle(int[] v) {
		int numValles = 0;// variable de control
		for (int i = 0; i < v.length; i++) {// bucle sobre el vector
			if (esValle(v, i)) {// condición de valle
				numValles++;// si es valle, incrementamos el valor de la variable de control
			}
		}
		return numValles;// devolvemos el valor de la variable de control
	}

	/**
	 * Devuelve el valor del pico más alto del vector
	 * 
	 * @param int[] : v vector de enteros
	 * @return int : valor del pico más alto
	 */
	public static int valorPicoMasAlto(int[] v) {
		int valorPicoMasAlto = 0;// variable de control
		for (int i = 0; i < v.length; i++) {// bucle sobre el vector
			if (esPico(v, i)) {// condición de pico
				if (v[i] > valorPicoMasAlto) {// si es pico y su valor es mayor que el valor del pico más alto
					valorPicoMasAlto = v[i];// cambiamos el valor de la variable de control
				}
			}
		}
		return valorPicoMasAlto;// devolvemos el valor de la variable de control
	}

	/**
	 * Método que devuelve true si el vector tiene meseta, false en caso contrario
	 * Se define meseta como una secuencia de elementos consecutivos que tienen el
	 * mismo valor
	 * 
	 * @param int[] : v vector de enteros
	 * @return boolean : true si tiene meseta, false en caso contrario
	 */
	public static boolean tieneMeseta(int[] v) {
		boolean tieneMeseta = false;// variable de control
		for (int i = 0; i < v.length - 1; i++) {// bucle sobre el vector
			if (v[i] == v[i + 1]) {// condición de meseta
				tieneMeseta = true;// si tiene meseta, cambiamos el valor de la variable de control
			}
		}
		return tieneMeseta;// devolvemos el valor de la variable de control
	}

	/**
	 * Método que cuenta el número de mesetas en el vectorS
	 * @param int[] : v vector de enteros
	 * @return int : número de mesetas
	 */
	public static int numeroMesetas(int[] v) {
		if (!tieneMeseta(v)) {// si no hay meseta, devolvemos 0
			return 0;			
		}
		int numMesetas = 0;// variable de control
		boolean esMeseta = false;// variable de control
		for (int i = 0; i < v.length - 1; i++) {// bucle sobre el vector
			if (v[i] == v[i + 1]) {// condición de meseta
				esMeseta = true;// si es meseta, cambiamos el valor de la variable de control
			} else {// si no es meseta
				if (esMeseta) {// se mira que haya sido meseta
					numMesetas++;// si ha sido meseta, incrementamos el valor de la variable de control
					esMeseta = false;// cambiamos el valor de la variable de control
				}
			}
		}
		return numMesetas;// devolvemos el valor de la variable de control
	}

}
