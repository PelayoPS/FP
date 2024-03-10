package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import src.Habitacion;
import src.Hotel;

/**
 * Clase de test para la clase Hotel
 * 
 * @version 1.1, 28/02/2021
 * @autor Pelayo Palacio Suárez
 */

public class HotelesTests {
	// Instancia de la clase Hotel
	private Hotel hotel = new Hotel();
	// Crea 10 habitaciones con cada uno de los tipos simple, doble, matrimonial y
	// especial
	private Habitacion habitacion1 = new Habitacion(1, 1, "simple", 50, false, true);
	private Habitacion habitacion2 = new Habitacion(2, 1, "simple", 50, false, true);
	private Habitacion habitacion3 = new Habitacion(3, 1, "doble", 75, false, true);
	private Habitacion habitacion4 = new Habitacion(4, 1, "doble", 75, false, true);
	private Habitacion habitacion5 = new Habitacion(5, 1, "matrimonial", 100, false, true);
	private Habitacion habitacion6 = new Habitacion(6, 1, "matrimonial", 100, false, true);
	private Habitacion habitacion7 = new Habitacion(7, 1, "especial", 150, false, true);
	private Habitacion habitacion8 = new Habitacion(8, 1, "especial", 150, false, true);
	private Habitacion habitacion9 = new Habitacion(9, 1, "especial", 150, false, true);
	private Habitacion habitacion10 = new Habitacion(10, 1, "especial", 150, false, true);

	/**
	 * Test añadir habitación
	 */
	@Test
	public void testNuevaHabitacion() {
		// añade una habitación
		hotel.nuevaHabitacion(habitacion1);
		// comprueba que la habitación ha sido añadida
		assertEquals(habitacion1, hotel.getHabitaciones()[0]);

		// añade las 9 habitaciones restantes
		hotel.nuevaHabitacion(habitacion2);
		hotel.nuevaHabitacion(habitacion3);
		hotel.nuevaHabitacion(habitacion4);
		hotel.nuevaHabitacion(habitacion5);
		hotel.nuevaHabitacion(habitacion6);
		hotel.nuevaHabitacion(habitacion7);
		hotel.nuevaHabitacion(habitacion8);
		hotel.nuevaHabitacion(habitacion9);
		hotel.nuevaHabitacion(habitacion10);

		// intenta añadir una habitación más
		Habitacion habitacion11 = new Habitacion(11, 1, "especial", 150, false, true);
		hotel.nuevaHabitacion(habitacion11);

		// comprueba que no se ha añadido la habitación

		boolean resultado = false;
		for (int i = 0; i < hotel.getHabitaciones().length; i++) {
			if (hotel.getHabitaciones()[i] == habitacion11) {
				resultado = true;
			}
		}
		assertFalse(resultado);

	}

	/**
	 * Test borrar habitación
	 */
	@Test
	public void testBorrarHabitacion() {
		// intenta borrar primera habitación

		hotel.borrarHabitacion(habitacion1.getIdentificador());

		// comprueba que la habitación ha sido borrada

		assertNull(hotel.getHabitaciones()[0]);

		// intenta borrar una habitación que no existe

		hotel.borrarHabitacion(1000);

		// intenta borrar una habitación que está ocupada

		hotel.nuevaHabitacion(habitacion1);
		habitacion1.setOcupada(true);
		hotel.borrarHabitacion(habitacion1.getIdentificador());

		// comprueba que la habitación no ha sido borrada

		assertNotNull(hotel.getHabitaciones()[0]);

	}
}
