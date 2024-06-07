package Actividades;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class Actividad7 {

	public static void main(String[] args) {
		/*
		 * Determinar si un año pedido por teclado es bisiesto
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduce el año");
		int year = keyboard.nextInt();
		//Google sabe que es así
		String msg = ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) ?
				"El año es bisiesto" :
				"El año no es bisiesto";
		System.out.println(msg);
		keyboard.close();
		
	}
	
	public static void main2(String[] args) {
		/*
		 * Determinar si un año pedido por teclado es bisiesto
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduce el año");
		int year = keyboard.nextInt();
		//Usando una librería que hace los cálculos y todo
		GregorianCalendar calendar = new GregorianCalendar();
		if (calendar.isLeapYear(year)) {
			System.out.println("El año es bisiesto");
		} else {
			System.out.println("El año no es bisiesto");
		}
		
		keyboard.close();
	}
}
