package Actividades;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Actividad20 {

	/**
	 * Dado el número de mes o nombre y año, comprobando si es bisiesto dar el
	 * número de días del mes
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * clase que guarda el nombre y días de un mes
		 */
		class Month {
			public String name;
			public int days;
			Month(String name, int days){
				this.name = name;
				this.days = days;
			}
		}
		
		Scanner keyboard = new Scanner(System.in);
		// lista con meses y sus días
		ArrayList<Month> months = new ArrayList<Month>();
		months.add(new Month("enero", 31));
		months.add(new Month("febrero", 28));
		months.add(new Month("marzo", 31));
		months.add(new Month("abril", 30));
		months.add(new Month("mayo", 31));
		months.add(new Month("junio", 30));
		months.add(new Month("julio", 31));
		months.add(new Month("agosto", 31));
		months.add(new Month("septiembre", 30));
		months.add(new Month("octubre", 31));
		months.add(new Month("noviembre", 30));
		months.add(new Month("diciembre", 31));

		GregorianCalendar gc = new GregorianCalendar();
		System.out.println("Introduce el año y mes(por nombre o número) con formato: año,mes");
		String[] content = keyboard.nextLine().split(",");// separa en año y mes
		keyboard.close();
		int year = Integer.parseInt(content[0]);// pasa el año a int
		String month = content[1];// el mes puede ser un nombre o un número
		int numberOfDays = 0;
		try {
			/*
			 * intenta parsear a int, si lo consigue es que el mes estaba como número, busca
			 * posición en la lista y devuelve los días si es bisiesto suma uno a febrero
			 */
			int monthNumber = Integer.parseInt(month);
			numberOfDays = (months.get(monthNumber - 1).days);
			if (gc.isLeapYear(year) && numberOfDays == 28) {
				numberOfDays++;
			}
		} catch (Exception e) {
			/*
			 * utiliza un stream para filtrar el elemento que tenga como "clave" el nombre del mes
			 * cuando lo encuentra devuelve los días y suma 1 a febrero si es año bisiesto
			 */
			numberOfDays = months.stream().filter(t -> t.name.equals(month)).toList().get(0).days;
			if (gc.isLeapYear(year) && numberOfDays == 28) {
				numberOfDays++;
			}
		}

		System.out.println("Number of days for year " + year + " and month " + month + " is: " + numberOfDays);

	}
	
}
