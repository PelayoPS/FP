package envios;

/**
 * Clase main que ejecuta la clase Envios
 * 
 * @autor Pelayo Palacio Suárez
 * @version 1.1
 */
public class EnviosEjec {

	public static void main(String[] args) {
		Persona p1 = new Persona("1111111a", "LUIS");
		Persona p2 = new Persona("2222222b", "JUAN");
		Envios paqueteJuan = new Envios(p1, p2, "urgente");
		System.out.println(paqueteJuan.toString());
		System.out.println("Tipo envio" + paqueteJuan.getTipo()
				+ "Importe Envio " + paqueteJuan.calculaImporte(5));

	}

}
