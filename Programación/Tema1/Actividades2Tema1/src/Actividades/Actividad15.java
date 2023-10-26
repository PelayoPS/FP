package Actividades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Actividad15 {

	public static void main(String[] args) {
		/*
		 * Calcula cual es el mayor, mediano y menor de tres números que se
		 * piden por teclado
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduce el primer número");
		float num = keyboard.nextFloat();
		System.out.println("Introduce el primer número");
		float num1 = keyboard.nextFloat();
		System.out.println("Introduce el primer número");
		float num2 = keyboard.nextFloat();
		//Mete en una lista los números y los ordena
		List<Float> floats = new ArrayList<Float>();
        floats.add(num);
        floats.add(num1);
        floats.add(num2);
        floats.sort(null);
        Collections.reverse(floats);
        //Al ser 3 el primero es mayor y el último el menor dejando en medio el mediano
        System.out.printf("Mayor: %.2f, Mediano: %.2f, Menor: %.2f",
        		floats.get(0),
        		floats.get(1),
        		floats.get(2));
		keyboard.close();
	}
}
