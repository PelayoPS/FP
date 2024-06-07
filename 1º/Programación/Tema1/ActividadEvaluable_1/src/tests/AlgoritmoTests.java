package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import src.Algoritmo;

public class AlgoritmoTests {

	@Test
	public void test() {
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(5);
		lista.add(10);
		lista.add(15);
		Algoritmo algoritmo = new Algoritmo();
		System.out.println(algoritmo.testMethod(lista));
		assertTrue(algoritmo.testMethod(lista).equals("La media de los números introducidos es: 10.0"));
	}
	
	@Test
	public void test1() {
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		lista.add(4);
		lista.add(3);
		Algoritmo algoritmo = new Algoritmo();
		System.out.println(algoritmo.testMethod(lista));
		assertTrue(algoritmo.testMethod(lista).equals("No se ha introducido ningún número múltiplo de 5"));
	}
	
	@Test
	public void test3() {
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		lista.add(5);
		lista.add(-1);
		Algoritmo algoritmo = new Algoritmo();
		System.out.println(algoritmo.testMethod(lista));
		assertTrue(algoritmo.testMethod(lista).equals("La media de los números introducidos es: 5.0"));
	}

}
