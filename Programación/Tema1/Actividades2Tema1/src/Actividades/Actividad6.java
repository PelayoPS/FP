package Actividades;

import java.util.Scanner;

public class Actividad6 {

	public static void main(String[] args) {
		/*
		 * Calcular la raíz cuadrada de un número que pediremos por teclado,
		 * en caso de ser negativo informar de que la operación no es posible
		 * sin math.sqrt
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduce el número");
		int num = keyboard.nextInt();
		if(num < 0) {
			System.err.println("No se puede hacer la raíz porque el número es negativo");
		} else {
			System.out.printf("Raíz: %d", squareRoot(num));
		}
		
		keyboard.close();
	}
	
	/**
	 * Bucle que calcula la raíz utilizando restas y un contador
	 * @param number
	 * @return
	 */
	private static int squareRoot(int number) {
		int counter = number/2;
		while((counter * counter) > number) {
			counter = counter - 1;
		}
		return counter;
	}
	
	public static void main2(String[] args) {
		/*
		 * Calcular la raíz cuadrada de un número que pediremos por teclado,
		 * en caso de ser negativo informar de que la operación no es posible
		 * con Math.sqrt
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduce el número");
		double num = keyboard.nextDouble();
		if(num < 0) {
			System.err.println("No se puede hacer la raíz porque el número es negativo");
		} else {
			System.out.printf("Raíz: %.2f", Math.sqrt(num));
		}
		
		keyboard.close();
	}
	
}
