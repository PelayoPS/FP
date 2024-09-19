package src;

import java.util.Scanner;

public class Actividad7 {
    public static void main(String[] args) {
	
		/*
		 * Transformador de C a F en ambos sentidos
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduce la unidad que quieras transformar (C | F)");
		String unit = keyboard.next().toUpperCase();
		System.out.println("Introduce los grados");
		double degrees = keyboard.nextDouble();
		//transforma según la opción elegida
		String result = unit.equals("C") ? 
				String.format("%.2f C -> %.2f F", degrees, fromCtoF(degrees)) :
				String.format("%.2f F -> %.2f C", degrees, fromFtoC(degrees));	
		System.out.printf("Resultado %s", result);
		
		keyboard.close();
		
	}
	
	private static double fromCtoF(double degrees) {
		return ((9.0/5)*degrees)+32;
	}
	
	private static double fromFtoC(double degrees) {
		return (degrees-32)*(5/9.0);
	}
    
}
