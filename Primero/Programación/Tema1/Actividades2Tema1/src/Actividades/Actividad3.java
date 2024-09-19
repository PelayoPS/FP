package Actividades;

import java.util.Scanner;

public class Actividad3 {

	public static void main(String[] args) {
		
		/*
		 * Calcular el área y la circunferencia de un círculo cuyo radio se debe
		 * preguntar al usuario
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduce el radio del círculo");
		float radius = keyboard.nextFloat();
		keyboard.close();
		//Comprueba los valores y avisa en caso de error
		if (radius <= 0) {
			System.err.println("El radio es negativo");
			System.exit(0);//no ejecuta el resto del código
		}
		double area = Math.PI * radius * radius;
		double circunference = 2 * Math.PI * radius;
		System.out.printf("Área: %.2f, Circunferencia: %.2f", area, circunference);
		
	}
}
