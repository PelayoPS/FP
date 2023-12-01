package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import src.Funciones;

public class Tests {

	//personal use to not check console output

	@Test
	public void primoTrue() {
		assertTrue(Funciones.esPrimo(17));
	}

	@Test
	public void primoFalse() {
		assertFalse(Funciones.esPrimo(20));
	}

	@Test
	public void siguientePrimo() {
		assertEquals(19, Funciones.siguientePrimo(17));
		assertEquals(23, Funciones.siguientePrimo(20));
	}

	@Test
	public void digitos() {
		assertEquals(4, Funciones.digitos(1234));
	}

	@Test
	public void voltea() {
		assertEquals(4321, Funciones.voltea(1234));
	}

	@Test
	public void digitoN() {
		assertEquals(3, Funciones.digitoN(1234, 1));
		assertEquals(4, Funciones.digitoN(1234, 0));
		assertEquals(-1, Funciones.digitoN(1234, 6));
	}

	@Test
	public void posicionDigito() {
		assertEquals(3, Funciones.posicionDeDigito(12345, 2));
		assertEquals(-1, Funciones.posicionDeDigito(12345, 6));
	}

	@Test
	public void quitarPorDetras() {
		assertEquals(1234, Funciones.quitaPorDetras(12345));
	}

	@Test
	public void quitarPorDelante() {
		assertEquals(2345, Funciones.quitaPorDelante(12345));
	}

	@Test
	public void pegarPorDetras() {
		assertEquals(123459, Funciones.pegaPorDetras(12345, 9));
	}

	@Test
	public void pegarPorDelante() {
		assertEquals(912345, Funciones.pegaPorDelante(12345, 9));
	}

}
