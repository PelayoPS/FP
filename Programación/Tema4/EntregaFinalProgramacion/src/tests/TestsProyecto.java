package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import src.Direccion;
import src.Entrenador;
import src.Equipo;
import src.Jugador;
import src.Persona;

class TestsProyecto {

	/**
	 * Test del constructor de la clase Equipo
	 */
	@Test
	public void testConstructor() {
		String nombreEquipo = "G2 Esports";
		Equipo equipo = new Equipo(nombreEquipo);

		assertEquals(nombreEquipo, equipo.getNombre());
		assertEquals(0, equipo.getMiembros().size());
	}

	/**
	 * Test del método agregarMiembro de la clase Equipo
	 */
	@Test
	public void testAgregarMiembro() {
		Equipo equipo = new Equipo("G2 Esports");
		Persona jugador1 = new Jugador("Caps", "Rasmus Winther", "12345678A", new Direccion("Direccion"), "G2 Esports",
				1);

		equipo.agregarMiembro(jugador1);

		assertEquals(1, equipo.getMiembros().size());
		assertTrue(equipo.getMiembros().contains(jugador1));
	}

	/**
	 * Test del método eliminarMiembro de la clase Equipo
	 */
	@Test
	public void testEliminarMiembro() {
		Equipo equipo = new Equipo("G2 Esports");
		Persona jugador1 = new Jugador("Caps", "Rasmus Winther", "12345678A", new Direccion("Direccion"), "G2 Esports",
				1);

		equipo.agregarMiembro(jugador1);
		equipo.eliminarMiembro(jugador1);

		assertEquals(0, equipo.getMiembros().size());
		assertFalse(equipo.getMiembros().contains(jugador1));
	}

	/**
	 * Test del método mostrarMiembros de la clase Equipo
	 */
	@Test
	public void testMostrarMiembros() {
		Equipo equipo = new Equipo("G2 Esports");
		Persona jugador1 = new Jugador("Caps", "Rasmus Winther", "12345678A", new Direccion("Direccion"), "G2 Esports",
				1);
		equipo.agregarMiembro(jugador1);

		// Se captura la salida del método mostrarMiembros
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		equipo.mostrarMiembros();

		// Se comprueba que la salida contiene la información de los dos miembros
		String expectedOutput = "Jugador{nombre='Caps', apellidos='Rasmus Winther', dni='12345678A', equipo='G2 Esports', numero=1}\r\n"
				+ //
				"";
		assertEquals(expectedOutput, out.toString());
	}

	/**
	 * Test del método numeroJugadores de la clase Equipo
	 */
	@Test
	public void testNumeroJugadores() {
		Equipo equipo = new Equipo("G2 Esports");
		Persona jugador1 = new Jugador("Caps", "Rasmus Winther", "12345678A", new Direccion("Direccion"), "G2 Esports",
				1);

		Persona entrenador = new Entrenador("Caps", "Rasmus Winther", "12345678A", new Direccion("Direccion"),
				"G2 Esports",
				"Coach");
		equipo.agregarMiembro(jugador1);
		equipo.agregarMiembro(entrenador);

		assertEquals(1, equipo.numeroJugadores());
	}

	/**
	 * Test del método numeroEntrenadores de la clase Equipo
	 */
	@Test
	public void testNumeroEntrenadores() {
		Equipo equipo = new Equipo("G2 Esports");
		Persona jugador1 = new Jugador("Caps", "Rasmus Winther", "12345678A", new Direccion("Direccion"), "G2 Esports",
				1);

		Persona entrenador = new Entrenador("Caps", "Rasmus Winther", "12345678A", new Direccion("Direccion"),
				"G2 Esports",
				"Coach");
		equipo.agregarMiembro(jugador1);
		equipo.agregarMiembro(entrenador);

		assertEquals(1, equipo.numeroEntrenadores());
	}

}
