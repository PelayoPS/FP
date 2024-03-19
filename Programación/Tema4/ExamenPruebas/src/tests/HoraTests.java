package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.Hora;

class HoraTests {


	/**
	 * Test positivo para el método horasIguales de la clase Hora
	 */
	@Test
	void horasIgualesTestPositivo() {
		Hora h1 = new Hora(12, 30, 45);
		Hora h2 = new Hora(12, 30, 45);
		assertTrue(h1.horasIguales(h1, h2));
	}

	/**
	 * Test negativo para el método horasIguales de la clase Hora
	 */
	@Test
	void horasIgualesTestNegativo() {
		Hora h1 = new Hora(12, 30, 45);
		Hora h2 = new Hora(13, 30, 45);
		assertFalse(h1.horasIguales(h1, h2));
	}

	/**
	 * Casos límite para el método horasIguales de la clase Hora
	 */
	@Test
	void horasIgualesTestLimites() {
		Hora h1 = new Hora(23, 59, 59);
		Hora h2 = new Hora(0, 0, 0);
		assertFalse(h1.horasIguales(h1, h2));
	}

	/**
	 * Test positivo para el método mayorQue de la clase Hora
	 */
	@Test
	void mayorQueTestPositivo() {
		Hora h1 = new Hora(13, 30, 45);
		Hora h2 = new Hora(12, 30, 45);
		assertTrue(h1.mayorQue(h1, h2));
	}

	/**
	 * Test positivo para el método mayorQue de la clase Hora
	 */
	@Test
	void mayorQueTestPositivo2() {
		Hora h1 = new Hora(12, 30, 45);
		Hora h2 = new Hora(12, 30, 45);
		assertTrue(h1.mayorQue(h1, h2));
	}

	/**
	 * Test negativo para el método mayorQue de la clase Hora
	 */
	@Test
	void mayorQueTestNegativo() {
		Hora h1 = new Hora(12, 30, 45);
		Hora h2 = new Hora(13, 30, 45);
		assertFalse(h1.mayorQue(h1, h2));
	}

	/**
	 * Casos límite para el método mayorQue de la clase Hora
	 */
	@Test
	void mayorQueTestLimites() {
		Hora h1 = new Hora(23, 59, 59);
		Hora h2 = new Hora(0, 0, 0);
		assertTrue(h1.mayorQue(h1, h2));
	}


}
