package Actividades;

import java.util.Scanner;

public class Actividad5 {

	public static void main(String[] args) {
		/*
		 * Determinar si un número leído por teclado es positivo o negativo
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduce el número");
		float num = keyboard.nextFloat();
		//Esto es un if puesto en pequeño
		String positiveOrNegative = num >= 0 ? "es positivo" : "es negativo";
		System.out.printf("%.2f: %s", num, positiveOrNegative);
		keyboard.close();
	}
}
