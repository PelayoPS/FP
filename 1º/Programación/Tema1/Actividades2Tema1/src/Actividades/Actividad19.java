package Actividades;

import java.util.Scanner;

public class Actividad19 {

	public static void main(String[] args) {
		/*
		 * Los empleados de una fábrica trabajan por turnos: diurno y nocturno. Se debe
		 * calcular el jornal diario de acuerdo con los siguientes puntos: 
		 * • La tarifa por horas diurnas es de 20€ 
		 * • La tarifa por horas nocturnas es de 35€ 
		 * • Caso de ser domingo, la tarifa se incrementa en 10€ más para el turno diurno y 15€
		 *   para el nocturno
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduce el turno y día con el formato: día,turno");
		String inputs = keyboard.next().toLowerCase();
		double tarifa = 0;
		switch(inputs) {
			case "domingo,diurno":
				tarifa = 30;
				break;
			case "domingo,nocturno":
				tarifa = 50;
				break;
			default:
				if(inputs.contains("diurno")) {
					tarifa = 20;
				} else {
					tarifa = 35;
				}
		}
		keyboard.close();
		System.out.println("El jornal diario es de: " + tarifa + "€");

	}
}
